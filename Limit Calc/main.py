import sympy as sp

def get_user_choice():
    print("Limit Calculator")
    print("1. Limit at Infinity")
    print("2. Limits Involving Indeterminate Forms")
    print("3. Basic Algebraic Limits")
    print("4. Trigonometric Limits")
    print("5. Exponential and Logarithmic Limits")
    print("6. Custom Limit")
    print("7. Exit")
    
    return input("Select the type of limit to solve (1/2/3/4/5/6/7): ")

def evaluate_limit(expression, x, point=None):
    func = sp.sympify(expression)
    if point is None:
        limit_result = sp.limit(func, x, sp.oo)
        limit_type = "Limit at Infinity"
    else:
        limit_result = sp.limit(func, x, point)
        limit_type = f"Limit as x approaches {point}"
    return func, limit_result, limit_type

def limit_calculator():
    x = sp.symbols('x')
    
    while True:
        choice = get_user_choice()
        
        if choice in ('1', '2', '4', '5'):
            expression = input("Enter the expression (use 'x' as the variable): ")
            if choice == '3' or choice == '6':
                point = float(input("Enter the value of x (approaching which the limit is to be evaluated): "))
            else:
                point = None
                
            func, limit_result, limit_type = evaluate_limit(expression, x, point)
            print(limit_type + ":")
            print("Expression:", func)
            print("Limit:", limit_result)
            print("\nSolution:")
            print(limit_result)
            print()
            
        elif choice == '3' or choice == '6':
            expression = input("Enter the expression (use 'x' as the variable): ")
            point = float(input("Enter the value of x (approaching which the limit is to be evaluated): "))
            func, limit_result, limit_type = evaluate_limit(expression, x, point)
            print(limit_type + ":")
            print("Expression:", func)
            print("Limit:", limit_result)
            print("\nSolution:")
            print(limit_result)
            print()
            
        elif choice == '7':
            print("Exiting the Limit Calculator.")
            break
        
        else:
            print("Invalid choice. Please select a valid option.\n")
            continue

if __name__ == "__main__":
    limit_calculator()
