package com.samsungsds.caasportal.yamlgenerator;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

import com.samsungsds.caasportal.common.ResourceFileLoader;

public final class VelocityUtil {
	public static String getConvertedStr(String path, Map<String, String> var) {
		VelocityEngine engine = new VelocityEngine();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "string");
        engine.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
        engine.addProperty("string.resource.loader.repository.static", "false");

        engine.init();
        
        StringResourceRepository repo = (StringResourceRepository) engine.getApplicationAttribute(StringResourceLoader.REPOSITORY_NAME_DEFAULT);
        repo.putStringResource("source", ResourceFileLoader.readTemplate(path));
        
		
		VelocityContext context = new VelocityContext();
		
        context.put("var", var);
        
        Template template = engine.getTemplate("source");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        
        return writer.toString();
	}
}
