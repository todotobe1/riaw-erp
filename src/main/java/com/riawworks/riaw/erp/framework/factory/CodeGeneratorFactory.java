package com.riawworks.riaw.erp.framework.factory;

import com.riawworks.riaw.erp.framework.generator.code.CodeGenerator;

public interface CodeGeneratorFactory extends Factory {

	public CodeGenerator create(String key);

}
