// provider "azurerm" {
//   version = "=2.39.0"
//   features {}
// }

provider "azurerm" {
  features {}
  subscription_id = "9ae5c229-9413-4920-bd6d-4b8b0ea5d15d"
  tenant_id       = "dbd6664d-4eb9-46eb-99d8-5c43ba153c61"
  client_id       = "10a074e3-e7dd-46ae-803b-4ffcedcf5363"
  client_secret       = "tH.8Q~THRDSPAwzAc8k_YQJwtqgnfZ4OG1gYIciY"
}

terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "2.78.0"
    }
  }
}


