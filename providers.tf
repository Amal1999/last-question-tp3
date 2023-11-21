// provider "azurerm" {
//   version = "=2.39.0"
//   features {}
// }

provider "azurerm" {
  features {}
  subscription_id = "9ae5c229-9413-4920-bd6d-4b8b0ea5d15d"
  tenant_id       = "dbd6664d-4eb9-46eb-99d8-5c43ba153c61"
}

terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "2.78.0"
    }
  }
}


