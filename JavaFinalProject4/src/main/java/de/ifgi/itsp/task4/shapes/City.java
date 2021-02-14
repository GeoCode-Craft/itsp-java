package de.ifgi.itsp.task4.shapes;

public class City extends  Shape{
    private String name;
    private Point location;
    private double populationDensity;
    private int population;
    private double areaKm2;
    private double gdpInBillions;
    private double foreignResidentsPercentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(int populationDensity) {
        this.populationDensity = populationDensity;
    }

    public double getAreaKm2() {
        return areaKm2;
    }

    public void setAreaKm2(double areaKm2) {
        this.areaKm2 = areaKm2;
    }

    public double getGdpInBillions() {
        return gdpInBillions;
    }

    public void setGdpInBillions(double gdpInBillions) {
        this.gdpInBillions = gdpInBillions;
    }

    public double getForeignResidentsPercentage() {
        return foreignResidentsPercentage;
    }

    public void setForeignResidentsPercentage(double foreignResidentsPercentage) {
        this.foreignResidentsPercentage = foreignResidentsPercentage;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", populationDensity=" + populationDensity +
                ", population=" + population +
                ", areaKm2=" + areaKm2 +
                ", gdpInBillions=" + gdpInBillions +
                ", foreignResidentsPercentage=" + foreignResidentsPercentage +
                '}';
    }
}
