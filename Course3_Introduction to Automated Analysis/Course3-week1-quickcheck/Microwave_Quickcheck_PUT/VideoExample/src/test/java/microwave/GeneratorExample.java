package microwave;

import java.util.ArrayList;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

public class GeneratorExample extends Generator<ConstructorExample> {

	public GeneratorExample() {
		super(ConstructorExample.class);
	}

	@Override
	public ConstructorExample generate(SourceOfRandomness random, GenerationStatus status) {

		ArrayList<Integer> il = new ArrayList<>();
		int maxSize = random.nextInt(50);
		for (int i = 0; i < maxSize; i++) {
			il.add(random.nextInt(20, 100));
		}
		
		int i = random.nextInt(30);
		
		Bar b = new Bar();
		b.setArg(random.nextInt());
		
		return new ConstructorExample(il, i, b);
	}

}
