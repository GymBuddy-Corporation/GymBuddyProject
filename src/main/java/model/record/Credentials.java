package model.record;

import java.io.Serializable;

public record Credentials(String email, String password) implements Serializable {}
