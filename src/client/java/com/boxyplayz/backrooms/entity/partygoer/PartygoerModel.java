package com.boxyplayz.backrooms.entity.partygoer;

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
public class PartygoerModel<T extends Entity> extends EntityModel<PartygoerRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			Identifier.fromNamespaceAndPath(BoxysBackrooms.MOD_ID, "partygoer"), "main");
	private final ModelPart Waist;
	@SuppressWarnings("unused")
	private final ModelPart Head;
	@SuppressWarnings("unused")
	private final ModelPart Body;
	private final ModelPart RightArm;
	@SuppressWarnings("unused")
	private final ModelPart Balloon;
	@SuppressWarnings("unused")
	private final ModelPart LeftArm;
	@SuppressWarnings("unused")
	private final ModelPart RightLeg;
	@SuppressWarnings("unused")
	private final ModelPart LeftLeg;

	public PartygoerModel(ModelPart root) {
		super(root);
		this.Waist = root.getChild("Waist");
		this.Head = this.Waist.getChild("Head");
		this.Body = this.Waist.getChild("Body");
		this.RightArm = this.Waist.getChild("Right Arm");
		this.Balloon = this.RightArm.getChild("Balloon");
		this.LeftArm = this.Waist.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Waist = partdefinition.addOrReplaceChild("Waist", CubeListBuilder.create(),
				PartPose.offset(0.0F, 12.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition Head = Waist.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F,
				-15.0F, -6.0F, 10.0F, 10.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -11.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition Body = Waist.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 21).addBox(-6.0F,
				-4.0F, -2.0F, 12.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition RightArm = Waist.addOrReplaceChild("Right Arm",
				CubeListBuilder.create().texOffs(42, 0).addBox(-5.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, -10.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition Balloon = RightArm.addOrReplaceChild("Balloon",
				CubeListBuilder.create().texOffs(56, 16)
						.addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
						.texOffs(0, 41).addBox(-3.0F, -3.0F, -13.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-3.0F, 10.0F, -3.0F));

		@SuppressWarnings("unused")
		PartDefinition LeftArm = Waist.addOrReplaceChild("LeftArm",
				CubeListBuilder.create().texOffs(0, 53).addBox(1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, -10.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(32, 21)
				.addBox(-4.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));

		@SuppressWarnings("unused")
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(32, 39)
				.addBox(-2.0F, 0.0F, -3.0F, 6.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}