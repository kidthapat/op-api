package com.op.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public class RememberMePersistentTokenRepository implements PersistentTokenRepository {
    @Autowired
    private RememberMeTokenRepository repository;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        repository.save(new RememberMeToken(null,
                token.getUsername(),
                token.getSeries(),
                token.getTokenValue(),
                token.getDate()));
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken token = repository.findBySeries(series);
        repository.save(new RememberMeToken(token.get_id(), token.getUsername(), series, tokenValue, lastUsed));
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return repository.findBySeries(seriesId);
    }

    @Override
    public void removeUserTokens(String username) {
        RememberMeToken token = repository.findByUsername(username);
        if (token != null) {
            repository.delete(token);
        }
    }
}
