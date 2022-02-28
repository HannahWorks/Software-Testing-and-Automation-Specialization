package microwave;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Fields;

public class TestGenerators {

    // Three different means of construction:
    //	Fields.class
    // 	Ctor.class
    //  GeneratorExample.class
    
    @Property(trials=5)
    public void testConstructorExample(@From(Fields.class) ConstructorExample c) {
    	System.out.println("object: " + c.toString());
    }


}
