package com.radixdlt.client.application.actions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import com.radixdlt.client.core.address.RadixAddress;
import com.radixdlt.client.core.atoms.TokenRef;
import java.math.BigDecimal;
import org.junit.Test;

public class TransferTokensActionTest {

	@Test
	public void testBadBigDecimalScale() {
		RadixAddress from = mock(RadixAddress.class);
		RadixAddress to = mock(RadixAddress.class);
		TokenRef tokenRef = mock(TokenRef.class);

		assertThatThrownBy(() -> TransferTokensAction.create(from, to, new BigDecimal("0.000001"), tokenRef))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void testSmallestAllowedAmount() {
		RadixAddress from = mock(RadixAddress.class);
		RadixAddress to = mock(RadixAddress.class);
		TokenRef tokenRef = mock(TokenRef.class);

		assertThat(TransferTokensAction.create(from, to, new BigDecimal("0.00001"), tokenRef).toString()).isNotNull();
	}

	@Test
	public void testSmallestAllowedAmountLargeScale() {
		RadixAddress from = mock(RadixAddress.class);
		RadixAddress to = mock(RadixAddress.class);
		TokenRef tokenRef = mock(TokenRef.class);

		assertThat(TransferTokensAction.create(from, to, new BigDecimal("0.000010000"), tokenRef).toString()).isNotNull();
	}
}