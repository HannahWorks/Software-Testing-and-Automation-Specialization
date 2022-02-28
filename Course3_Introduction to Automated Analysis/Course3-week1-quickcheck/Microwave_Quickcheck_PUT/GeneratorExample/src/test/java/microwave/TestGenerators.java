package microwave;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.Ctor;
import com.pholser.junit.quickcheck.generator.Fields;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;

@RunWith(JUnitQuickcheck.class)
public class TestGenerators {

    // Three different means of construction:
    //	Fields.class
    // 	Ctor.class
    //  GeneratorExample.class
    
    @Property(trials=5)
    public void testConstructorExample(@From(GeneratorExample.class) ConstructorExample c) {
    	System.out.println("object: " + c.toString() + ".");
    }


}
