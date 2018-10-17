package com.radixdlt.client.core.atoms.particles;

import com.google.gson.annotations.SerializedName;
import com.radixdlt.client.core.atoms.RadixHash;
import com.radixdlt.client.core.atoms.TokenRef;
import com.radixdlt.client.core.address.EUID;
import com.radixdlt.client.core.atoms.AccountReference;
import com.radixdlt.client.core.crypto.ECPublicKey;
import com.radixdlt.client.core.serialization.Dson;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TokenParticle implements Particle {
	public enum MintPermissions {
		GENESIS_ONLY,
		SAME_ATOM_ONLY,
		POW
	}

	private final String iso;
	private final String name;
	private final String description;
	private final byte[] icon;
	private final EUID uid;
	private final Spin spin;
	private final List<AccountReference> addresses;
	@SerializedName("mint_permissions")
	private final MintPermissions mintPermissions;

	public TokenParticle(
		AccountReference accountReference,
		String name,
		String iso,
		String description,
		MintPermissions mintPermissions,
		byte[] icon
	) {
		this.addresses = Collections.singletonList(accountReference);
		this.iso = iso;
		this.spin = Spin.UP;
		// FIXME: bad hack
		this.uid = RadixHash.of(Dson.getInstance().toDson(getTokenRef())).toEUID();
		this.name = name;
		this.description = description;
		this.mintPermissions = mintPermissions;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public String getIso() {
		return iso;
	}

	public String getDescription() {
		return description;
	}

	public TokenRef getTokenRef() {
		return TokenRef.of(addresses.get(0), iso);
	}

	public Set<ECPublicKey> getAddresses() {
		return Collections.singleton(addresses.get(0).getKey());
	}

	public Spin getSpin() {
		return spin;
	}
}