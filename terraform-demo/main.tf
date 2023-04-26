terraform {
  required_providers {
    google = {
      source = "hashicorp/google"
      version = "4.51.0"
    }
  }
}

provider "google" {
  credentials = "../../terraform_basics/service-DevOps.json"

  project = "devops2023-383617"
  region  = "us-central1"
  zone    = "us-central1-a"
}

resource "google_compute_instance" "vm_instance" {
  name = "instance-demo"
  machine_type = "e2-medium"

  boot_disk {
    initialize_params {
      image = "centos-cloud/centos-7"
    }
  }

  network_interface {
    network = "default"
    access_config {
    }
  }

  metadata_startup_script = <<-SCRIPT
  #!/bin/bash
  sudo apt-get update
  sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
  sudo docker pull -a drankrock/devops_project_2023
  sudo docker run drankrock/devops_project_2023
SCRIPT
}
