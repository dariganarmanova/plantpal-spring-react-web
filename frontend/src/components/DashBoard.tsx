import axios from "axios";
import React, { useState, useEffect } from "react";
import "./main.css";

interface DashboardData {
  name: string;
  location: string;
  age: number;
  description: string;
  watered: string;
  shouldBeWatered: boolean;
}

const DashBoard: React.FC = () => {
  const [dashboard, setDashboard] = useState<DashboardData[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      setError("User is not logged in. Please log in.");
      setLoading(false);
      return;
    }

    const fetchPlantData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/plantRetrieve",
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
        console.log(response.data)
        if (Array.isArray(response.data)) {
            setDashboard(response.data);
        } else {
            setError("Invalid data format received from the server")
        }
      } catch (error) {
        console.error(error);
        setError("Failed to fetch data. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    fetchPlantData();
  }, []);

  if (loading) return <p>Loading...</p>; // Display while loading

  if (error) return <p>{error}</p>; // Display errors

  return (
    <div className="dashboardData">
      {dashboard.length > 0 ? (
        dashboard.map((plant, index) => (
          <div key={index} className="dashboardContainer">
            <h1>{plant.name}</h1>
            <p>Location: {plant.location}</p>
            <p>Age: {plant.age}</p>
            <p>Description: {plant.description}</p>
            <p>Last Watered: {plant.watered}</p>
            <p>Should be watered?: {plant.shouldBeWatered ?  "Yes" : "No"}</p>
            <hr />
          </div>
        ))
      ) : (
        <p>There is no saved data of yours.</p>
      )}
    </div>
  );
};

export default DashBoard;