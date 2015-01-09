package com.riawworks.riaw.erp.framework.generator.code;

import com.riawworks.riaw.erp.framework.generator.Generator;

public abstract class CodeGenerator implements Generator {

	public abstract Boolean support(String key);

	public abstract String generate() throws Exception;

}
