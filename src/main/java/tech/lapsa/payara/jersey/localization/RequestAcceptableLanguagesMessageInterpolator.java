package tech.lapsa.payara.jersey.localization;

import java.util.List;
import java.util.Locale;

import javax.validation.MessageInterpolator;

public class RequestAcceptableLanguagesMessageInterpolator implements MessageInterpolator {

    private final MessageInterpolator delegate;

    public RequestAcceptableLanguagesMessageInterpolator(final MessageInterpolator delegate) {
	this.delegate = delegate;
    }

    @Override
    public String interpolate(final String message, final Context context) {
	final List<Locale> locales = RequestAcceptableLanguages.getLocalesList();
	if (locales != null)
	    for (final Locale locale : locales)
		try {
		    return delegate.interpolate(message, context, locale);
		} catch (final Exception e) {
		    // continue to interpolating in next listed locale
		}

	return delegate.interpolate(message, context);
    }

    @Override
    public String interpolate(final String message, final Context context, final Locale locale) {
	return delegate.interpolate(message, context, locale);
    }
}
