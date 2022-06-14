package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.mapper.PersistentLoginMapper;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class PersistentLoginServiceImpl implements PersistentTokenRepository {

    @Resource
    PersistentLoginMapper persistentLoginMapper;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        persistentLoginMapper.insertToken(token);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentRememberMeToken token =
                new PersistentRememberMeToken(null, series, tokenValue, lastUsed);
        persistentLoginMapper.updateToken(token);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return persistentLoginMapper.selectTokenBySeries(seriesId);
    }

    @Override
    public void removeUserTokens(String username) {
        persistentLoginMapper.deleteTokenByUsername(username);
    }
}
