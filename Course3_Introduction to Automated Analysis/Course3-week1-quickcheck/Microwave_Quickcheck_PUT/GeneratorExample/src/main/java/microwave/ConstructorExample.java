package microwave;

import java.util.ArrayList;
import java.util.List;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.generator.Fields;

public class ConstructorExample {

	List<Integer> l;	// Standard container type
	int i;				// basic type
	Bar b; 				// User defined type

	/*
	public ConstructorExample() {
		l = new ArrayList<>();
		i = 1;
		b = new Bar();
	}
	*/


	
	
	
	
	public ConstructorExample(ArrayList<Integer> l, int i,
			@From(Fields.class) Bar b) {
		this.l = l;
		this.i = i;
		this.b = b;
	}
	
	public String toString() {
		String s = "";
		if (l != null) {
			s += "l = " + l + "; ";
		}
		s += "i = " + i; 
		return s;
	}

}
