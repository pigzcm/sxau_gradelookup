package yfdc;
import androidx.annotation.Keep;
/**
 * @author Tom Smith
 * @since 1.0
 * this is an empty java Factory interface
 */
@Keep public interface Factory{
	public abstract static class FactImpl implements Factory{
		@Keep public FactImpl(){
			super();
			java();
		}
		@Keep private void java(){}
	}
}
