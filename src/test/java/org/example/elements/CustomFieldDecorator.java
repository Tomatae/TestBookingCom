package org.example.elements;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;

public class CustomFieldDecorator extends DefaultFieldDecorator {

    public CustomFieldDecorator(SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<?> decoratable = decoratable(field);

        if (decoratable == null) {
            return super.decorate(loader, field);
        }

        ElementLocator locator = factory.createLocator(field);

        if (locator == null) {
            return null;
        }
        return createElement(loader, locator, decoratable);
    }

    private Class<?> decoratable(Field field) {
        Class<?> dclass = field.getType();

        try {
            dclass.getConstructor(WebElement.class);
        } catch (Exception e) {
            return null;
        }

        return dclass;
    }

    protected <T> T createElement(ClassLoader loader, ElementLocator locator, Class<T> dclass) {
        WebElement proxy = proxyForLocator(loader, locator);
        return createInstance(dclass, proxy);
    }

    private <T> T createInstance(Class<T> dclass, WebElement element) {
        try {
            return dclass.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new AssertionError("WebElement is not a " + dclass);
        }
    }
}
