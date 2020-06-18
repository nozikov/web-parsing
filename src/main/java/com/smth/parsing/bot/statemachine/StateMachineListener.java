package com.smth.parsing.bot.statemachine;

import com.smth.parsing.bot.statemachine.enums.Events;
import com.smth.parsing.bot.statemachine.enums.States;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

@Slf4j
public class StateMachineListener extends StateMachineListenerAdapter<States, Events> {

  @Override
  public void stateChanged(State<States, Events> from, State<States, Events> to) {
    log.trace("Order state changed to {}", to.getId());
  }

  @Override
  public void transition(Transition<States, Events> transition) {
    log.trace("move from:{} to:{}",
        ofNullableState(transition.getSource()),
        ofNullableState(transition.getTarget()));
  }

  @Override
  public void eventNotAccepted(Message<Events> event) {
    log.error("event not accepted: {}", event);
  }

  private Object ofNullableState(State s) {
    return Optional.ofNullable(s)
        .map(State::getId)
        .orElse(null);
  }

}
