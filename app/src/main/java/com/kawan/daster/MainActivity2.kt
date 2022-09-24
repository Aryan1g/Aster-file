package com.kawan.daster
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.filament.utils.HDRLoader
import dev.romainguy.kotlin.math.lookAt
import io.github.sceneview.SceneView
import io.github.sceneview.environment.loadEnvironment
import io.github.sceneview.math.Direction
import io.github.sceneview.math.Position
import io.github.sceneview.node.ModelNode






class MainActivity2 : AppCompatActivity() {
    lateinit var sceneView: SceneView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        sceneView =findViewById(R.id.sceneview)
        val modelNode = ModelNode(
            position = Position(x = 0.0f, y = 0.0f,z = 0.0f),
            //  rotation = Rotation(x = 15f)
        )
        sceneView.addChild(modelNode)
        sceneView.cameraNode.transform = lookAt(
            eye = modelNode.worldPosition.let {
                Position(x = it.x - 0.28f, y = it.y + 0.2f, z = it.z + 0.1f)
            },
            target = modelNode.worldPosition,
            up = Direction(y = 1.0f)
        )
        lifecycleScope.launchWhenCreated {
            sceneView.environment = HDRLoader.loadEnvironment(
                context = this@MainActivity2,
                lifecycle = lifecycle,
                hdrFileLocation = "environments/studio_small_08_1k.hdr",
                specularFilter = true
            )?.apply {
                indirectLight?.intensity = 50_000f
            }


            modelNode.loadModel(
                context = this@MainActivity2,
                lifecycle = lifecycle,
                glbFileLocation = "models/male.glb",
                scaleToUnits = 0.34f,
                centerOrigin = Position(x = 1.4f, y = -0.1f, z = 0.2f)
            )


            //   delay(1500)
            /**     sceneView.cameraNode.smooth(
            lookAt(
            eye = modelNode.worldPosition.let {
            Position(x = it.x - 0.4f, y = it.y + 0.4f, z = it.z - 0.6f)
            },
            target = modelNode.worldPosition,
            up = Direction(y = 1.0f)
            ),
            speed = 0.7f
            )      **/

        }


        }
}