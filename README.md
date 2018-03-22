# PropertyUtils

Java code commonly needs to query system properties for configuration. Often, these checks are
given default values to use if the property is not set.

The problem comes when code is run under a
[SecurityManager](https://docs.oracle.com/javase/10/docs/api/java/lang/SecurityManager.html)
that denies the property read attempts. Instead of the `null` that is expected from trying
to read a property that does not exist, a
[SecurityException](https://docs.oracle.com/javase/10/docs/api/java/lang/SecurityException.html)
is thrown, stopping the execution of the application.

The following code is often used to read a property. But this code either requires time-consuming
discovery of the properties to be read and changes to the security policy to allow the read, or it
will simply fail and not be able to run under a Security Manager.

```java
String value = System.getProperty("key");
```

The proper way to read a system property requires the read to be wrapped in a `try` so that failures
are caught and dealt with. While correct, sprinkling this all over your code results in a lower
signal to noise ratio and makes it harder to see at a glance what is done:

```java
String value = null;
try {
  value = System.getProperty("key");
} catch(SecurityException e) {
}
```

PropertyUtils allows you to not care why the property read failed and just continue on as
though the property was not set. It eliminates boilerplate required for robustly reading
a property value.

When property reads fail either because the property is not set or because the SecurityManager
denied the read attempt a `null` or a default value will be returned. No exceptions are thrown
and code can ignore that it may be running under a restrictive SecurityManager.

## Examples

```java
import com.eagen.propertyutils.PropertyUtils;
String value = PropertyUtils.getSystemProperty("key");
String val_or_default = PropertyUtils.getSystemProperty("key", "default");
```
