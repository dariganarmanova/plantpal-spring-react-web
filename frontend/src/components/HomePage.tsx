import React, { FormEvent, useState, useEffect } from "react";
import axios from "axios";
import "./main.css";
import { useNavigate } from "react-router-dom";

interface Plant {
  name: string;
  location: string;
  age: number;
  description: string;
  watered: string;
}

const HomePage: React.FC = () => {
  const [input, setInput] = useState<Plant>({
    name: "",
    location: "",
    age: 0,
    description: "",
    watered: "",
  });
  const [data, setData] = useState<Plant[]>([]);
  const [submiss, setSubmiss] = useState<boolean>(false);
  useEffect(() => {
    if (submiss) {
      setTimeout(() => {
        setSubmiss(false);
      }, 2000);
    }
  }, [submiss])
  useEffect(() => {
    const token = localStorage.getItem("token");
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/plants", {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-type": "application/json",
          },
        });
        console.log(response.data);
        setData(response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    e.preventDefault();
    const { name, value } = e.target;
    setInput((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();
    if (submiss) return;
    const token = localStorage.getItem("token");
    console.log(token);
    try {
      const response = await axios.post(
        "http://localhost:8080/api/plant",
        input,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
      console.log(response.data);
      if (response.status === 200) {
        const newData = [...data,response.data]
        setData(newData);
        setSubmiss(true);
        setInput({
          name: "",
          location: "",
          age: 0,
          description: "",
          watered: "",
        })
        alert("Your data was successfuly saved");
      }
    } catch (error) {
      console.log(error);
    }
  };
  const navigate = useNavigate();
  const change = () => {
    navigate("/dashboard");
  };
  return (
    <div className="formInput">
      <div className="formContainer">
        <form onSubmit={handleSubmit}>
          <div className="imageWrapper">
            {" "}
            <img
              className="imagePlant"
              src={require("./assets/planta.jpg")}
              alt="plantImage"
            />
          </div>
          <button onClick={change}>
            Press the button to go to your Dashboard
          </button>
          <label>
            {" "}
            Name of your plant ^^:
            <input
              type="text"
              name="name"
              value={input.name}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Location (your home):
            <input
              type="text"
              name="location"
              value={input.location}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Days since plant was owned:
            <input
              type="number"
              name="age"
              value={input.age}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Describe why you got your plant:
            <input
              type="text"
              name="description"
              value={input.description}
              onChange={handleInputChange}
            />
          </label>
          <label>
            Last watered (write in this format 2025-01-17T14:30:00):
            <input
              type="text"
              name="watered"
              value={input.watered}
              onChange={handleInputChange}
            />
          </label>
          <button type="submit">Enter</button>
        </form>
      </div>
      <div className="listContainer">
        {data.length > 0 ? (
          <ul>
            {data.map((item, index) => (
              <div className="itemContainer">
                <li key={index}>
                <p>Name of your plant: {item.name}</p>
                  <p>Age of your plant: {item.age} </p>
                  <p>Description of your plant: {item.description}</p> 
                  <p>Location of your plant: {item.location} </p>
                  <p>Last watered: {item.watered}</p>
                </li>
              </div>
            ))}
          </ul>
        ) : (
          <p>No data available for now</p>
        )}
      </div>
    </div>
  );
};

export default HomePage;