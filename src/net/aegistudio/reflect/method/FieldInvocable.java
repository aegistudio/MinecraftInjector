package net.aegistudio.reflect.method;

import java.lang.reflect.Field;

public class FieldInvocable implements Invocable {
	private final Field field;
	public FieldInvocable(Field field) {
		this.field = field;
	}
	
	@Override
	public Object invoke(Object source, Object... paramList) throws Exception {
		if(paramList.length == 0)
			return field.get(source);
		else {
			field.set(source, paramList[0]);
			return null;
		}
	}

	@Override
	public String getName() {
		return field.getName();
	}

	@Override
	public Class<?>[] getParameterList() {
		return new Class<?>[] { field.getType() };
	}

	@Override
	public void setAccessible(boolean accessible) {
		field.setAccessible(accessible);
	}

	@Override
	public Class<?> getReturnType() {
		return field.getType();
	}
}
