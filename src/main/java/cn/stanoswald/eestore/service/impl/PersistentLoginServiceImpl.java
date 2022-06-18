package cn.stanoswald.eestore.service.impl;

import cn.stanoswald.eestore.mapper.PersistentLoginMapper;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Service
public class PersistentLoginServiceImpl implements PersistentTokenRepository {

    @Resource
    PersistentLoginMapper persistentLoginMapper;

    public void removeUserTokensByCookie(String rememberMeCookie) throws UnsupportedEncodingException {
        String[] tokens = decodeCookie(rememberMeCookie);
        String presentedSeries = tokens[0];

        PersistentRememberMeToken token = getTokenForSeries(presentedSeries);
        removeUserTokens(token.getUsername());
    }

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

    protected String[] decodeCookie(String cookieValue) throws InvalidCookieException, UnsupportedEncodingException {
        for (int j = 0; j < cookieValue.length() % 4; ++j) {
            cookieValue = cookieValue + "=";
        }
        String cookieAsPlainText;
        try {
            cookieAsPlainText = new String(Base64.getDecoder().decode(cookieValue.getBytes()));
        } catch (IllegalArgumentException var7) {
            throw new InvalidCookieException("Cookie token was not Base64 encoded; value was '" + cookieValue + "'");
        }
        String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, ":");
        for (int i = 0; i < tokens.length; ++i) {
            tokens[i] = URLDecoder.decode(tokens[i], StandardCharsets.UTF_8.toString());
        }

        return tokens;
    }
}
