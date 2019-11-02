package com.chetdeva.madrasi.root.onboarding;

import com.ubercab.test.UberTestBase;
import com.uber.rib.core.RouterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OnboardingRouterTest extends UberTestBase {

  @Mock private LandingScope scope;
  @Mock private OnboardingInteractor interactor;
  @Mock private OnboardingView view;

  private OnboardingRouter router;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    router = new OnboardingRouter(scope, view, interactor);
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  public void anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router);
    RouterHelper.detach(router);

    throw new RuntimeException("Remove this test and add real tests.");
  }

}
