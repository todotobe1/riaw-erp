package com.riawworks.riaw.erp.framework.factory.impl;

import java.util.ArrayList;
import java.util.List;

import com.riawworks.riaw.erp.framework.factory.CodeGeneratorFactory;
import com.riawworks.riaw.erp.framework.generator.code.CodeGenerator;

public class CodeGeneratorFactoryImpl implements CodeGeneratorFactory {

	private List<CodeGenerator> codeGenerators;

	public CodeGenerator create(String key) {
		for (CodeGenerator codeGenerator : codeGenerators) {
			if (codeGenerator.support(key))
				return codeGenerator;
		}
		return null;
	}

	public synchronized void addCodeGenerator(CodeGenerator codeGenerator) {
		if (this.codeGenerators == null) {
			this.codeGenerators = new ArrayList<CodeGenerator>();
		}
		this.codeGenerators.add(codeGenerator);
	}
}
