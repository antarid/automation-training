const PassengerPlane = require("./PassengerPlane");
const MilitaryPlane = require("./MilitaryPlane");
const ExperimentalPlane = require("./ExperimentalPlane");
const MilitaryType = require("../types/military");

class Airport {
  constructor(planes) {
    this.planes = planes;
  }

  getPlanes() {
    return this.planes;
  }

  getExperimentalPlanes() {
    return this.planes.filter(plane => plane instanceof ExperimentalPlane);
  }

  getPassengerPlanes() {
    return this.planes.filter(plane => plane instanceof PassengerPlane);
  }

  getPassengerPlaneWithMaxPassengersCapacity() {
    return this.sortByPassengersCapacity()
      .getPlanes()
      .slice(-1)[0];
  }

  getMilitaryPlanes() {
    return this.planes.filter(plane => plane instanceof MilitaryPlane);
  }

  getTransportMilitaryPlanes() {
    return this.getMilitaryPlanes().filter(
      militaryPlane => militaryPlane.militaryType === MilitaryType.Transport
    );
  }

  getBomberMilitaryPlanes() {
    return this.getMilitaryPlanes().filter(
      militaryPlane => militaryPlane.militaryType === MilitaryType.Bomber
    );
  }

  sortByMaxDistance() {
    this.planes.sort((a, b) =>
      a.maxFlightDistance > b.maxFlightDistance ? 1 : -1
    );
    return this;
  }

  sortByMaxSpeed() {
    this.planes.sort((a, b) => (a.maxSpeed > b.maxSpeed ? 1 : -1));
    return this;
  }

  sortByMaxLoadCapacity() {
    this.planes.sort((a, b) =>
      a.maxLoadCapacity > b.maxLoadCapacity ? 1 : -1
    );
    return this;
  }

  sortByPassengersCapacity() {
    this.planes.sort((a, b) =>
      a.passengersCapacity > b.passengersCapacity ? 1 : -1
    );
    return this;
  }

  static print(planes) {
    return JSON.stringify(planes);
  }
}

module.exports = Airport;
