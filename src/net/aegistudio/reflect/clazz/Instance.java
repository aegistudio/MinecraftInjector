package net.aegistudio.reflect.clazz;

public class Instance<Clazz extends AbstractClass> {
	public final Clazz clazz;
	public final Object thiz;

	public Instance(Clazz clazz, Object instance) throws IllegalArgumentException {
		this.clazz = clazz;
		if(!clazz.isInstance(instance)) throw new IllegalArgumentException();
		this.thiz = instance;
	}

	public <NewClazz extends AbstractClass> Instance<NewClazz> cast(NewClazz newClazz) {
		return new Instance<NewClazz>(newClazz, this.thiz);
	}
}
