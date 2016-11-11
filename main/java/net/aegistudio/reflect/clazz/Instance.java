package net.aegistudio.reflect.clazz;

public class Instance<Clazz extends Class> {
	public final Clazz clazz;
	public final Object thiz;

	public Instance(Clazz clazz, Object instance) throws ClassCastException {
		this.clazz = clazz;
		if(clazz != null)
			if(!clazz.isInstance(instance)) 
				throw new ClassCastException("Instance of class " + 
						instance.getClass() + " cannot be cast to " + clazz );
		
		this.thiz = instance;
	}

	public <NewClazz extends AbstractClass> Instance<NewClazz> cast(NewClazz newClazz) {
		return new Instance<NewClazz>(newClazz, this.thiz);
	}
}
