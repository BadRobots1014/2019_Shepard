package frc.robot.driver;

import badlog.lib.BadLog;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.log.Loggable;

public class Xbox360Controller extends XboxController implements Loggable {
    private double joystickDeadband = 0.1;
    private double triggerDeadband = 0.1;
    private double joystickScale = 1.0;

    public Xbox360Controller(int port) {
        super(port);
        initLogging();
    }

    public Xbox360Controller(int port, double joystickDeadband, double triggerDeadband) {
        super(port);
        this.joystickDeadband = joystickDeadband;
        this.triggerDeadband = triggerDeadband;
    }

    public boolean getLeftBumper() {
        return getBumper(Hand.kLeft);
    }

    public boolean getRightBumper() {
        return getBumper(Hand.kRight);
    }

    @Override
    public double getY(Hand hand) {
        return Math.abs(super.getY(hand)) > joystickDeadband ? super.getY(hand) * joystickScale : 0;
    }

    public double getLeftY() {
        return getY(Hand.kLeft);
    }

    public double getRightY() {
        return getY(Hand.kRight);
    }

    public boolean getLeftYActive() {
        return getLeftY() != 0;
    }

    public boolean getRightYActive() {
        return getRightY() != 0;
    }

    @Override
    public double getTriggerAxis(Hand hand) {
        return Math.abs(super.getTriggerAxis(hand)) > triggerDeadband ? super.getTriggerAxis(hand)
                : 0;
    }

    public double getLeftTrigger() {
        return getTriggerAxis(Hand.kLeft);
    }

    public double getRightTrigger() {
        return getTriggerAxis(Hand.kRight);
    }

    public boolean getLeftTriggerPulled() {
        return getLeftTrigger() != 0;
    }

    public boolean getRightTriggerPulled() {
        return getRightTrigger() != 0;
    }

    public void setJoystickDeadband(double joystickDeadband) {
        this.joystickDeadband = joystickDeadband;
    }

    public void setTriggerDeadband(double triggerDeadband) {
        this.triggerDeadband = triggerDeadband;
    }

    public double getJoystickScale() {
        return joystickScale;
    }

    public void setJoystickScale(double joystickScale) {
        this.joystickScale = joystickScale;
    }

    @Override
    public void initLogging() {
        String name = getClass().getSimpleName();
        BadLog.createTopic(name + "Left Y", BadLog.UNITLESS, () -> getY(Hand.kLeft));
        BadLog.createTopic(name + "Right Y", BadLog.UNITLESS, () -> getY(Hand.kRight));
        BadLog.createTopic(name + "Left X", BadLog.UNITLESS, () -> getX(Hand.kLeft));
        BadLog.createTopic(name + "Right X", BadLog.UNITLESS, () -> getX(Hand.kRight));
        BadLog.createTopic(name + "Left Trigger", BadLog.UNITLESS,
                () -> getTriggerAxis(Hand.kLeft));
        BadLog.createTopic(name + "Right Trigger", BadLog.UNITLESS,
                () -> getTriggerAxis(Hand.kRight));
    }
}
