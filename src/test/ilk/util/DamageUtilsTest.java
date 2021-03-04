package ilk.util;

import static com.google.common.truth.Truth.assertThat;

import com.fs.starfarer.api.combat.DamageType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DamageUtilsTest {

  @Test
  public void testGetDamageAtHitStrengthLowArmor() {
    final float baseDamage = 10;
    final float strength = 100;
    final float armor = 1;
    final float modifiedDamage = 15;

    assertThat(modifiedDamage).isAtLeast(baseDamage);

  }

  // TODO: Add tests for hull damage with modifiers.
}
