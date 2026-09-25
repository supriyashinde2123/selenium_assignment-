package PlayWright_Day5;

import com.microsoft.playwright.Locator;

public class CustomeAsseration {
    private final Locator locator;


    CustomeAsseration(Locator locator) {
        this.locator = locator;
    }

    public static CustomeAsseration assertThat(Locator locator) {
        return new CustomeAsseration(locator);
    }

    public CustomeAsseration hasCssClass(String className) {
        String actualClass = locator.getAttribute("class");
        System.out.println("In Custom Assertion");
        System.out.println("Locator: " + locator.toString());

        if (actualClass == null || !actualClass.contains(className)) {
            throw new AssertionError(
                    "Expected element to have class '" + className + "' but found: " + actualClass);
        }
        return this;
    }
}
