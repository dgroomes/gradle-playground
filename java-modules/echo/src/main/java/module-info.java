module dgroomes.java_modules.echo {
    requires com.fasterxml.jackson.databind;

    // Export only the exact "dgroomes.java_modules.echo" package. Notice how we don't export the package "dgroomes.java_modules.echo.impl".
    // This means other code that imports the "dgroomes.java_modules.echo" module will only see the "dgroomes.java_modules.echo.Echo"
    // class and will not see the "dgroomes.echo.java_modules.impl.Util class". The Java Module System offers strong encapsulation!
    exports dgroomes.java_modules.echo;
}
