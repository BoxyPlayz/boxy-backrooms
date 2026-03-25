package com.boxyplayz.backrooms.entity.skinstealer;

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

public class SkinStealerModel<T extends Entity> extends EntityModel<SkinStealerRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "smiler"), "main");
	private final ModelPart core;
	@SuppressWarnings("unused")
	private final ModelPart mouth;
	@SuppressWarnings("unused")
	private final ModelPart eyeL;
	@SuppressWarnings("unused")
	private final ModelPart eyeR;

	public SkinStealerModel(ModelPart root) {
		super(root);
		this.core = root.getChild("root");
		this.mouth = this.core.getChild("mouth");
		this.eyeL = this.core.getChild("eyeL");
		this.eyeR = this.core.getChild("eyeR");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition mouth = root.addOrReplaceChild("mouth",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-4.0F, -2.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 4).addBox(-6.0F, -4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(0, 8).addBox(-8.0F, -6.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(8, 8).addBox(6.0F, -6.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
						.texOffs(8, 4).addBox(4.0F, -4.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -2.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition eyeL = root.addOrReplaceChild("eyeL", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F,
				-18.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition eyeR = root.addOrReplaceChild("eyeR", CubeListBuilder.create().texOffs(8, 12).addBox(3.0F,
				-18.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}