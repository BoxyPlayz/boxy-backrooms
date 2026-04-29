package com.boxyplayz.backrooms.entity.balloon;

import com.boxyplayz.backrooms.BoxysBackrooms;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class BalloonModel<T extends Entity> extends EntityModel<BalloonRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "balloon"), "main");
	@SuppressWarnings("unused")
	private final ModelPart Balloon;

	public BalloonModel(ModelPart root) {
		super(root);
		this.Balloon = root.getChild("Balloon");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		@SuppressWarnings("unused")
		PartDefinition Balloon = partdefinition.addOrReplaceChild("Balloon",
				CubeListBuilder.create().texOffs(0, 16)
						.addBox(-1.0F, -11.0F, -1.0F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 0).addBox(-4.0F, -19.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}