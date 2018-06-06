package Annotations;


/*
Choice A: Annotations and Reflection
Write an annotation called ProperLength that is meant to describe the
required length of String input.

Here are the requirements for the annotation:
	This annotation has two elements:
		min length (default 1)
		max length (default 255)

	The annotation is meant for fields (instance data variables) only.
Apply this annotation to some or all the fields of
the provided Address class, with these specifications:
	street must not be empty and can be a max of 255
	street2 has no restrictions (it can be empty and there is no max)
	city must not be empty and can be a max of 255
	state must be exactly 2 characters long
	zip must be exactly 5 characters long

After applying the annotation, implement the validateLengths method.
	This method uses reflection to look over all fields in the Address class
	and validate them using the annotation.

	If a field has invalid length, an exception is thrown.

I've provided some pseudocode in the file that might be helpful.
(You're not required to follow the pseudocode.)
 */

import com.sun.istack.internal.NotNull;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Address {

//    street must not be EMPTY and can be a max of 255
    @ProperLength
	private String street;

    //    street2 has no restrictions (it can be empty and there is no max)
	private String street2;

	//    city must not be empty and can be a max of 255
    @ProperLength
	private String city;

    //    state must be exactly 2 characters long
    @ProperLength(
            min = 2,
            max = 2
    )
	private String state;

    //    zip must be exactly 5 characters long
    @ProperLength(
            min = 5,
            max = 5
    )
	private String zip;

	public Address(String street, String street2, String city, String state, String zip) {
		this.street = street;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;

        validateLengths();



	}

	
	private void validateLengths() {
		Object object = this;
		Field[] fields = object.getClass().getDeclaredFields();
        boolean allFieldsValid = true;
        String value = "";
        String message = "";
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(ProperLength.class))
            {
                ProperLength annotation = field.getAnnotation(ProperLength.class);
                try
                {
                    value = (String) field.get(object);
                } catch (IllegalAccessException ex)
                {
                    ex.printStackTrace();
                }
                if (value.length() > annotation.max() || value.length() < annotation.min())
                {
                    allFieldsValid = false;
                    message = value + " has an invalid length for a " + field.getName();
                }
            }
            //On last element of fields array
            if (field.equals(fields[fields.length - 1]))
            {
                if(allFieldsValid)
                {
                    System.out.println(value);
                }
                else
                {
                    throw new IllegalArgumentException(message);
                }
            }
        }//fields for-each loop
	}
	
	@Override
	public String toString() {
		String s = street + 
				(street2.length()>0 ? "\n"+street2 : "") +
				"\n" + city + ", " + state + " " + zip;
		return s;
				
	}

	
	

}
