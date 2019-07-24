package ru.neoflex.nfcore.base.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.neoflex.nfcore.base.auth.Authenticator;
import ru.neoflex.nfcore.base.auth.PasswordAuthenticator;
import ru.neoflex.nfcore.base.util.DocFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetail implements UserDetailsService {

    @Autowired
    Store store;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        String password = null;
        String role = null;

        if(userName == null){
            return null;
        }

        DocFinder docFinder = DocFinder.create(store);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode selector = objectMapper.createObjectNode();
        selector
                .with("contents")
                .put("eClass", "ru.neoflex.nfcore.base.auth#//User")
                .put("name", userName);

        try {
            docFinder
                    .executionStats(true)
                    .selector(selector)
                    .execute();

            EList<Resource> resources = docFinder.getResourceSet().getResources();
            if (resources.isEmpty()) {
                return null;
            }
            ru.neoflex.nfcore.base.auth.User user = (ru.neoflex.nfcore.base.auth.User) resources.get(0)
                    .getContents()
                    .get(0);

            for (Authenticator authenticator : user.getAuthenticators()) {
                if (!authenticator.isDisabled() && authenticator instanceof PasswordAuthenticator) {
                    PasswordAuthenticator passwordAuthenticator = (PasswordAuthenticator) authenticator;
                    password = passwordAuthenticator.getPassword();
                    break;
                }
            }
            if (!user.getRoles().isEmpty()) {
                role = user.getRoles().get(0).getName();
            }

        } catch (IOException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }

        List<GrantedAuthority> au = new ArrayList<>();
        au.add(new SimpleGrantedAuthority(role));

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails userDetails = new User(userName, encoder.encode(password), true, true, true, true,
                au);
        return userDetails;
    }

}
