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

  getPassengerPlanes() {
    return this.planes.filter(plane => plane instanceof PassengerPlane);
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

  getExperimentalPlanes() {
    return this.planes.filter(plane => plane instanceof ExperimentalPlane);
  }

  getPassengerPlaneWithMaxPassengersCapacity() {
    let passengerPlanes = this.getPassengerPlanes();
    let planeWithMaxCapacity = passengerPlanes[0];
    for (let i = 0; i < passengerPlanes.length; i++) {
      if (
        passengerPlanes[i].passengersCapacity >
        planeWithMaxCapacity.passengersCapacity
      ) {
        planeWithMaxCapacity = passengerPlanes[i];
      }
    }
    return planeWithMaxCapacity;
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

  static print(planes) {
    return JSON.stringify(planes);
  }
}

module.exports = Airport;
