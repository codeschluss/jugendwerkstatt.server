package app.wooportal.server.core.error;

import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherResult;
import io.leangen.graphql.execution.InvocationContext;
import io.leangen.graphql.execution.ResolverInterceptor;

public class ExceptionResolverInterceptor implements ResolverInterceptor {

  @Override
  public Object aroundInvoke(InvocationContext context, Continuation continuation)
      throws Exception {
    try {
      return continuation.proceed(context);
    } catch (Exception e) {
      return DataFetcherResult.newResult()
          .error(GraphqlErrorBuilder
              .newError(context.getResolutionEnvironment().dataFetchingEnvironment)
              .message("Hure").build())
          .build();
    }
  }
}
