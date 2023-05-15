# Use the official MongoDB image as the base
FROM mongo:latest

# Set the working directory in the container
WORKDIR /data
ENV MONGO_DATA_DIR /data/db
# Copy the MongoDB configuration file into the container
COPY ../../../../init.js /docker-entrypoint-initdb.d/

# Set the environment variables for the MongoDB configuration


# Expose the default MongoDB port (27017)
EXPOSE 27017

# Start the MongoDB server when the container runs
CMD ["mongod"]