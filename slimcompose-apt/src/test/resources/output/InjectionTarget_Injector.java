import com.slimgears.slimcompose.injection.DependencyInjector;
import com.slimgears.slimcompose.injection.PerInjection;

import dagger.Component;

@PerInjection
@Component(dependencies = {TestComponent1.class, TestComponent2.class})
interface InjectionTarget_Injector extends DependencyInjector<InjectionTarget> {}
