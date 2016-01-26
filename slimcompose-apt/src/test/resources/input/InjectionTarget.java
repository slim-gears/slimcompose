import com.slimgears.slimcompose.injection.InjectFrom;
import dagger.Component;

@Component
interface TestComponent1 {
}

@Component
interface TestComponent2 {
}

@InjectFrom({TestComponent1.class, TestComponent2.class})
class InjectionTarget {
}
