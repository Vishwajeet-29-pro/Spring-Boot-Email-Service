package com.emailhandler.email.dto;

import java.util.Properties;

public class MailConfigDto {
    int port;
    String host;
    String username;
    String password;
    String protocol;
    Properties properties;

    public MailConfigDto(int port, String host, String username, String password, String protocol, Properties properties) {
        this.port = port;
        this.host = host;
        this.username = username;
        this.password = password;
        this.protocol = protocol;
        this.properties = properties;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
