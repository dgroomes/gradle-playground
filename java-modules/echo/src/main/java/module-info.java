module dgroomes.echo {
    requires com.fasterxml.jackson.databind;

    // Export only the exact "dgroomes.echo" package. Notice how we don't export the package "dgroomes.echo.impl". This
    // means other code that imports the "dgroomes.echo" module will only see the dgroomes.echo.Echo class and will not
    // see the dgroomes.echo.impl.Util class. The Java Module System offers strong encapsulation!
    exports dgroomes.echo;
}
