package org.jbake.launcher;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(
        description = "JBake is a Java based, open source, static site/blog generator for developers & designers",
        name = "jbake"
)
public class LaunchOptions {
    @CommandLine.Parameters(index = "0", description = "source folder of site content (with templates and assets), if not supplied will default to current directory", arity = "0..1")
    private String source;

    @CommandLine.Parameters(index = "1", description = "destination folder for output, if not supplied will default to a folder called \"output\" in the current directory", arity = "0..1")
    private String destination;

    @CommandLine.Option(names = {"-b", "--bake"}, description = "performs a bake")
    private boolean bake;

    @CommandLine.Option(names = {"-i", "--init"}, description = "initialises required folder structure with default templates (defaults to current directory if <value> is not supplied)", arity = "0..1")
    private boolean init;

    @CommandLine.Option(names = {"-t", "--template"}, description = "use specified template engine for default templates (uses Freemarker if <value> is not supplied) ", arity = "1")
    private String template;

    @CommandLine.Option(names = {"-s", "--server"}, description = "runs HTTP server to serve out baked site, if no <value> is supplied will default to a folder called \"output\" in the current directory")
    private boolean runServer;

    @CommandLine.Option(names = {"-h", "--help"}, description = "prints this message", usageHelp = true)
    private boolean helpRequested;

    @CommandLine.Option(names = {"--reset"}, description = "clears the local cache, enforcing rendering from scratch")
    private boolean clearCache;

    public String getTemplate() {
        if (template != null) {
            return template;
        } else {
            return "freemarker";
        }
    }

    public File getSource() {
        if (source != null) {
            return new File(source);
        } else {
            return new File(System.getProperty("user.dir"));
        }
    }

    public String getSourceValue() {
        return source;
    }

    public File getDestination() {
        if (destination != null) {
            return new File(destination);
        } else {
            return new File(getSource(), "output");
        }
    }

    public String getDestinationValue() {
        return destination;
    }

    public boolean isHelpNeeded() {
        return helpRequested || !(isBake() || isRunServer() || isInit() || source != null || destination != null);
    }

    public boolean isRunServer() {
        return runServer;
    }

    public boolean isInit() {
        return init;
    }

    public boolean isClearCache() {
        return clearCache;
    }

    public boolean isBake() {
        return bake || (source != null && destination != null);
    }
}
