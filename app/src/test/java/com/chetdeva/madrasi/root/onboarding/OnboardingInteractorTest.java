package com.chetdeva.madrasi.root.onboarding;

import com.ubercab.test.UberTestBase;
import com.uber.rib.core.InteractorHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OnboardingInteractorTest extends UberTestBase {

  @Mock private OnboardingInteractor.OnboardingPresenter presenter;
  @Mock private OnboardingRouter router;

  private OnboardingInteractor interactor;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    interactor = TestLandingInteractor.create(presenter);
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  public void anExampleTest_withSomeConditions_shouldPass() {
    // Use InteractorHelper to drive your interactor's lifecycle.
    InteractorHelper.attach(interactor, presenter, router, null);
    InteractorHelper.detach(interactor);

    throw new RuntimeException("Remove this test and add real tests.");
  }

}
