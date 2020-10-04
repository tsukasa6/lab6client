package core.commands;

import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {
    private static final long serialVersionUID = 32L;
    protected abstract void writeInfo();
    protected abstract void execute(String[] args) throws IOException, ClassNotFoundException, InterruptedException;
}
