# Getting Started

Insurance company wants to start issuing private property policies to their customers. System analysts found out that
there will be a policy which will have objects (e.g. a House) and that objects will have sub-objects (e.g. eletronic
devices such as TV). One policy can contain multiple objects. One object can contain multiple sub-objects.

In this iteration, customer needs a functionality that calculates premium (a price that will be paid by each client that
will buy this insurance) for the policy. Premium is calculated by a formula defined in "Needed functionality" section.
In short - formula groups all sub-objects by their type, sums their sum-insured and applies coefficient to the sum. Then
all group sums are summed up which gets us a premium that must be paid by the client. No GUI is needed, policy data will
be sent through the API directly to the methods that will be created. No database is needed, functionality should not
store any data. It should receive policy object, calculate premium and return result. Preferred invocation of the
functionality but may be changed if needed:

```PremiumCalculator#calculate(Policy policy);```

## Needed functionality

In this iteration client stated that only risk types FIRE and THEFT will be calculated, however it may be possible that
in near future more risk types will be added. Make sure that it is easy to extend implementation for new risk types.
Premium calculation formula:

```
PREMIUM = PREMIUM_FIRE + PREMIUM_THEFT
PREMIUM_FIRE = SUM_INSURED_FIRE * COEFFICIENT_FIRE
SUM_INSURED_FIRE - total sum insured of all policy's sub-objects with type "Fire"
COEFFICIENT_FIRE - by default 0.014 but if SUM_INSURED_FIRE is over 100 then 0.024
PREMIUM_THEFT = SUM_INSURED_THEFT * COEFFICIENT_THEFT
SUM_INSURED_THEFT - total sum insured of all policy's sub-objects with type "Theft" 
COEFFICIENT_THEFT - by default 0.11 but if SUM_INSURED_THEFT equal or greater than 15 then 0.05
```

# Installation

Clone the repo:

```
git clone git@github.com:alexhandzhiev/ProofIT.git
```

Go to your <PROJECT_FOLDER> and run:

```
./gradlew bootRun
```

# Usage

Open your favourite REST client application. Set request method to:
```POST```
Set request path to:

```
http://localhost:8080/calculate
```

Set request body to (ex. json payload ahead):

```json
{
  "number": "LV20-02-100000-5",
  "status": "REGISTERED",
  "insurables": [
    {
      "name": "House",
      "subInsurables": [
        {
          "name": "Fridge",
          "riskType": "FIRE",
          "insuranceSum": 100.00
        },
        {
          "name": "Toaster",
          "riskType": "THEFT",
          "insuranceSum": 8.00
        }
      ]
    }
  ]
}

```

## Tests

Running tests:

```
./gradlew clean test --info
```

Test results:

```
<PROJECT_FOLDER>/build/reports/tests/test/index.html
```
