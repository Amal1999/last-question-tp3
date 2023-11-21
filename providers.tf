// provider "azurerm" {
//   version = "=2.39.0"
//   features {}
// }

provider "azurerm" {
  features {}
  subscription_id = "9ae5c229-9413-4920-bd6d-4b8b0ea5d15d"
  tenant_id       = "dbd6664d-4eb9-46eb-99d8-5c43ba153c61"
  client_id       = "53dceb8c-612b-46de-a815-b51f761e167c"
  client_secret       = "Ba58Q~FWpckS.HD5s39HGpXR.GYmA~U_mMGiTabX"
}

terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "2.78.0"
    }
  }
}


