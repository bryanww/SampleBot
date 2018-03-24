package com.github.bryanww.samplebot;

    import com.github.ocraft.s2client.api.S2Client;
    import com.github.ocraft.s2client.api.controller.S2Controller;
    import com.github.ocraft.s2client.api.rx.Responses;
    import com.github.ocraft.s2client.protocol.game.BattlenetMap;
    import com.github.ocraft.s2client.protocol.game.Difficulty;
    import com.github.ocraft.s2client.protocol.response.*;
    import com.github.ocraft.s2client.protocol.spatial.Point;
    import com.github.ocraft.s2client.protocol.spatial.PointI;
    import com.github.ocraft.s2client.protocol.unit.Tag;

    import java.nio.file.Paths;

    import static com.github.ocraft.s2client.api.S2Client.starcraft2Client;
    import static com.github.ocraft.s2client.api.controller.S2Controller.starcraft2Game;
    import static com.github.ocraft.s2client.protocol.action.Action.action;
    import static com.github.ocraft.s2client.protocol.action.Actions.Raw.cameraMove;
    import static com.github.ocraft.s2client.protocol.action.Actions.Raw.unitCommand;
    import static com.github.ocraft.s2client.protocol.action.Actions.Spatial.click;
    import static com.github.ocraft.s2client.protocol.action.Actions.Ui.selectArmy;
    import static com.github.ocraft.s2client.protocol.action.spatial.ActionSpatialUnitSelectionPoint.Type.TOGGLE;
    import static com.github.ocraft.s2client.protocol.data.Abilities.TRAIN_SCV;
    import static com.github.ocraft.s2client.protocol.data.Units.TERRAN_COMMAND_CENTER;
    import static com.github.ocraft.s2client.protocol.game.ComputerPlayerSetup.computer;
    import static com.github.ocraft.s2client.protocol.game.PlayerSetup.participant;
    import static com.github.ocraft.s2client.protocol.game.Race.*;
    import static com.github.ocraft.s2client.protocol.request.Requests.*;

public class SampleBot {


    public static void main(String[] args) {
        S2Controller game = starcraft2Game().withExecutablePath(Paths.get("E:/Program Files (x86)/StarCraft II")).launch();
        S2Client client = starcraft2Client().connectTo(game).traced(true).start();
        client.request(createGame()
            .onBattlenetMap(BattlenetMap.of("Lava Flow"))
            .withPlayerSetup(participant(), computer(PROTOSS, Difficulty.MEDIUM)));
        client.responseStream()
            .takeWhile(Responses.isNot(ResponseLeaveGame.class))
            .subscribe(response -> {
                response.as(ResponseCreateGame.class).ifPresent(r -> client.request(joinGame().as(TERRAN)));
                response.as(ResponseJoinGame.class).ifPresent(r -> {
                    client.request(actions().of(
//                            action().raw(unitCommand().forUnits(Tag.of(TERRAN_COMMAND_CENTER)).useAbility(TRAIN_SCV))
//                            action().raw(cameraMove().to(Point.of(10, 10))),
                            action().featureLayer(click().on(PointI.of(15, 10)).withMode(TOGGLE)),
                            action().ui(selectArmy().add())
                    ));
                    client.request(nextStep());
//                    client.request(leaveGame());
                });
            });
    }
}
