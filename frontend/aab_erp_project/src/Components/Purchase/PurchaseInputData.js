import React, { useState, useEffect } from 'react';
import search from '../Images/search.png';
import imports from '../Images/Import.svg';
import deleteIcon from '../Images/Delete.svg';
import edit from '../Images/Edit.svg';
import Select from "react-select";
const PurchaseInputData = () => {
  const [isShowModal, setIsShowModal] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [category, setCategory] = useState("");
  const [model, setModel] = useState("");
  const [typeColor, setTypeColor] = useState("");
  const [brand, setBrand] = useState("");
  const [poCategory, setPoCategory] = useState([]);
  const [poCategorySearch, setPoCategorySearch] = useState('');
  const [poModel, setPoModel] = useState([]);
  const [poModelSearch, setPoModelSearch] = useState('');
  const [poBrand, setPoBrand] = useState([]);
  const [poBrandSearch, setPoBrandSearch] = useState('');
  const [poType, setPoType] = useState([]);
  const [poTypeSearch, setPoTypeSearch] = useState('');
  const [categoryOptions, setCategoryOptions] = useState([]);
  const [modelOptions, setModelOptions] = useState([]);
  const [brandOptions, setBrandOptions] = useState([]);
  const [typeColorOptions, setTypeColorOptions] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [isPopupOpen, setIsPopupOpen] = useState(false);
  const [isPopupOpen1, setIsPopupOpen1] = useState(false);
  const [isPopupOpen2, setIsPopupOpen2] = useState(false);
  const [isPopupOpen3, setIsPopupOpen3] = useState(false);
  const [editCategory, setEditCategory] = useState('');
  const [selectedCategoryId, setSelectedCategoryId] = useState('');
  const [isCategoryEditPopupOpen, setIsCategoryEditPopupOpen] = useState(false);
  const [editModel, setEditModel] = useState('');
  const [selectedModelId, setSelectedModelId] = useState('');
  const [isModelEditPopupOpen, setIsModelEditPopupOpen] = useState(false);
  const [editBrand, setEditBrand] = useState('');
  const [selectedBrandId, setSelectedBrandId] = useState('');
  const [isBrandEditPopupOpen, setIsBrandEditPopupOpen] = useState(false);
  const [editTypeColor, setEditTypeColor] = useState('');
  const [selectedTypeId, setSelectedTypeId] = useState('');
  const [isTypeEditPopupOpen, setIsTypeEditPopupOpen] = useState(false);
  const [poItemName, setPoItemName] = useState([]);
  const [poItemNameSearch, setPoItemNameSearch] = useState('');
  const [poItemNameId, setPoItemNameId] = useState('');
  const [isItemNameEditPopupOpen, setIsItemNameEditPopupOpen] = useState(false)
  const [poItemList, setPoItemList] = useState({
    itemName: '',
    category: '',
    otherPOEntityList: [
      {
        modelName: '',
        brandName: '',
        typeColor: '',
        minimumQty: '',
        defaultQty: ''
      }
    ]
  });
  useEffect(() => {
    setPoItemList((prev) => ({
      ...prev,
      category: selectedCategory ? selectedCategory.value : '',
    }));
  }, [selectedCategory]);

  const [poEditItemList, setPoEditItemList] = useState({
    itemName: '',
    category: '',
    otherPOEntityList: [
      {
        modelName: '',
        brandName: '',
        typeColor: '',
        minimumQty: '',
        defaultQty: ''
      }
    ]
  });
useEffect(() => {
    setPoEditItemList((prev) => ({
      ...prev,
      category: selectedCategory ? selectedCategory.value : '',
    }));
  }, [selectedCategory]);
  const [fields, setFields] = useState([
    { label: 'Item Name', inputType: 'text', dropdown: 'Dropdown' },
    { label: 'Model', inputType: 'text', dropdown: 'Dropdown' },
    { label: 'Type', inputType: 'text', dropdown: 'Dropdown' },
    { label: 'Quantity', inputType: 'text', dropdown: 'Dropdown' }
  ]);

  const openEditCategory = (item) => {
    setEditCategory(item.category)
    setSelectedCategoryId(item.id)
    setIsCategoryEditPopupOpen(true)
  }
  const openEditModel = (item) => {
    setEditModel(item.model)
    setSelectedModelId(item.id)
    setIsModelEditPopupOpen(true)
  }
  const openEditBrand = (item) => {
    setEditBrand(item.brand)
    setSelectedBrandId(item.id)
    setIsBrandEditPopupOpen(true)
  }
  const openEditTypeColor = (item) => {
    setEditTypeColor(item.typeColor)
    setSelectedTypeId(item.id)
    setIsTypeEditPopupOpen(true)
  }
  const openEditItemName = (item) => {
    setPoEditItemList(item)
    setPoItemNameId(item.id)
    setIsItemNameEditPopupOpen(true)
  }
  const closeEditCategory = (item) => {
    setEditCategory('')
    setSelectedCategoryId('')
    setIsCategoryEditPopupOpen(false)
  }
  const closeEditModel = (item) => {
    setEditModel('')
    setSelectedModelId('')
    setIsModelEditPopupOpen(false)
  }
  const closeEditBrand = (item) => {
    setEditBrand('')
    setSelectedBrandId('')
    setIsBrandEditPopupOpen(false)
  }
  const closeEditTypeColor = (item) => {
    setEditTypeColor('')
    setSelectedTypeId('')
    setIsTypeEditPopupOpen(false)
  }
  const handleAddField = () => {
    setFields([...fields, { label: '', inputType: 'text', dropdown: 'Dropdown' }]);
  };
  const handleSubmitEditCategory = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_category/edit/${selectedCategoryId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ category: editCategory }),
      });
      if (response.ok) {
        closeEditCategory();
        window.location.reload();
      } else {
        console.error('Failed to update floor name');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }
  const handleSubmitEditModel = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_model/edit/${selectedModelId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ model: editModel }),
      });
      if (response.ok) {
        closeEditModel();
        window.location.reload();
      } else {
        console.error('Failed to update floor name');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }
  const handleSubmitEditBrand = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_brand/edit/${selectedBrandId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ brand: editBrand }),
      });
      if (response.ok) {
        closeEditBrand();
        window.location.reload();
      } else {
        console.error('Failed to update floor name');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }
  const handleSubmitEditType = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_type/edit/${selectedTypeId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ typeColor: editTypeColor }),
      });
      if (response.ok) {
        closeEditTypeColor();
        window.location.reload();
      } else {
        console.error('Failed to update floor name');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  }
  const handleSubmitEditItemName = async (e) => {
    e.preventDefault();
    console.log('Sending data:', poEditItemList);
    try {
      const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_itemNames/edit/${poItemNameId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify( poEditItemList ),
      });
      if (response.ok) {
  const result = await response.json();
  console.log('Server response:', result);
} else {
  const errorResult = await response.text(); // or .json() if your backend returns JSON errors
  console.error('Failed to update:', errorResult);
}

    } catch (error) {
      console.error('Error:', error);
    }
  }
  const handleCategoryDelete = async (id) => {
    const confirmed = window.confirm("Are you sure you want to delete This Category?");
    if (confirmed) {
      try {
        const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_category/delete/${id}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          alert("Category deleted successfully!!!");
          window.location.reload();
        } else {
          console.error("Failed to delete the Payment Mode. Status:", response.status);
          alert("Error deleting the Payment Mode. Please try again.");
        }
      } catch (error) {
        console.error("Error:", error);
        alert("An error occurred while deleting the Payment Mode.");
      }
    } else {
      console.log("Cancelled");
    }
  };
  const handleModelDelete = async (id) => {
    const confirmed = window.confirm("Are you sure you want to delete This Model?");
    if (confirmed) {
      try {
        const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_model/delete/${id}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          alert("Model deleted successfully!!!");
          window.location.reload();
        } else {
          console.error("Failed to delete the Payment Mode. Status:", response.status);
          alert("Error deleting the Model. Please try again.");
        }
      } catch (error) {
        console.error("Error:", error);
        alert("An error occurred while deleting the Model");
      }
    } else {
      console.log("Cancelled");
    }
  };
  const handleBrandDelete = async (id) => {
    const confirmed = window.confirm("Are you sure you want to delete This Brand?");
    if (confirmed) {
      try {
        const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_brand/delete/${id}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          alert("Brand deleted successfully!!!");
          window.location.reload();
        } else {
          console.error("Failed to delete the Brand. Status:", response.status);
          alert("Error deleting the Brand. Please try again.");
        }
      } catch (error) {
        console.error("Error:", error);
        alert("An error occurred while deleting the Brand.");
      }
    } else {
      console.log("Cancelled");
    }
  };
  const handleTypeDelete = async (id) => {
    const confirmed = window.confirm("Are you sure you want to delete This Type?");
    if (confirmed) {
      try {
        const response = await fetch(`https://backendaab.in/aabuildersDash/api/po_type/delete/${id}`, {
          method: 'DELETE',
        });
        if (response.ok) {
          alert("Type delete successfully!!!");
          window.location.reload();
        } else {
          console.error("Failed to delete the Type. Status:", response.status);
          alert("Error deleting the Type. Please try again.");
        }
      } catch (error) {
        console.error("Error:", error);
        alert("An error occurred while deleting the Type.");
      }
    } else {
      console.log("Cancelled");
    }
  }
  const handleSubmitCategary = async (e) => {
    e.preventDefault();
    const newAccountType = { category };
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_category/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAccountType),
      });
      if (response.ok) {
        console.log('Account Type saved successfully!');
        setCategory('');
        window.location.reload();
      } else {
        console.log('Error saving area name.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error saving area name.');
    }
  };
  const handleSubmitModel = async (e) => {
    e.preventDefault();
    const newAccountType = { model };
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_model/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAccountType),
      });
      if (response.ok) {
        console.log('Account Type saved successfully!');
        setModel('');
        window.location.reload();
      } else {
        console.log('Error saving area name.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error saving area name.');
    }
  };
  const handleSubmitBrand = async (e) => {
    e.preventDefault();
    const newAccountType = { brand };
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_brand/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAccountType),
      });
      if (response.ok) {
        console.log('Account Type saved successfully!');
        setBrand('');
        window.location.reload();
      } else {
        console.log('Error saving area name.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error saving area name.');
    }
  };
  const handleSubmitType = async (e) => {
    e.preventDefault();
    const newAccountType = { typeColor };
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_type/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newAccountType),
      });
      if (response.ok) {
        console.log('Account Type saved successfully!');
        setTypeColor('');
        window.location.reload();
      } else {
        console.log('Error saving area name.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error saving area name.');
    }
  };
  useEffect(() => {
    fetchPoCategory();
  }, []);
  const fetchPoCategory = async () => {
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_category/getAll');
      if (response.ok) {
        const data = await response.json();
        setPoCategory(data);
        const options = data.map(item => ({
          value: item.category,
          label: item.category,
        }));
        setCategoryOptions(options);
      } else {
        console.log('Error fetching tile area names.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error fetching tile area names.');
    }
  };
  useEffect(() => {
    fetchPoModel();
  }, []);
  const fetchPoModel = async () => {
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_model/getAll');
      if (response.ok) {
        const data = await response.json();
        setPoModel(data);
        const options = data.map(item => ({
          value: item.model,
          label: item.model,
        }));
        setModelOptions(options)
      } else {
        console.log('Error fetching tile area names.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error fetching tile area names.');
    }
  };
  useEffect(() => {
    fetchPoBrand();
  }, []);
  const fetchPoBrand = async () => {
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_brand/getAll');
      if (response.ok) {
        const data = await response.json();
        setPoBrand(data);
        const options = data.map(item => ({
          value: item.brand,
          label: item.brand,
        }));
        setBrandOptions(options)
      } else {
        console.log('Error fetching tile area names.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error fetching tile area names.');
    }
  };
  useEffect(() => {
    fetchPoType();
  }, []);
  const fetchPoType = async () => {
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_type/getAll');
      if (response.ok) {
        const data = await response.json();
        setPoType(data);
        const options = data.map(item => ({
          value: item.typeColor,
          label: item.typeColor,
        }));
        setTypeColorOptions(options)
      } else {
        console.log('Error fetching tile area names.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error fetching tile area names.');
    }
  };
  useEffect(() => {
    fetchPoItemName();
  }, []);
  const fetchPoItemName = async () => {
    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_itemNames/getAll');
      if (response.ok) {
        const data = await response.json();
        setPoItemName(data);
        console.log(data)
      } else {
        console.log('Error fetching tile area names.');
      }
    } catch (error) {
      console.error('Error:', error);
      console.log('Error fetching tile area names.');
    }
  };
  const handleRemoveField = (index) => {
    const updatedFields = [...fields];
    updatedFields.splice(index, 1);
    setFields(updatedFields);
  };
  const filteredPocategory = poCategory.filter((item) =>
    item.category.toLowerCase().includes(poCategorySearch.toLowerCase())
  );
  const filteredPoModel = poModel.filter((item) =>
    item.model.toLowerCase().includes(poModelSearch.toLowerCase())
  );
  const filteredPoBrand = poBrand.filter((item) =>
    item.brand.toLowerCase().includes(poBrandSearch.toLowerCase())
  );
  const filteredPoTypeColor = poType.filter((item) =>
    (item.typeColor ?? '').toLowerCase().includes((poTypeSearch ?? '').toLowerCase())
  );
  const filteredPoItemName = poItemName.filter((item) =>
    (item.itemName ?? '').toLowerCase().includes((poItemNameSearch ?? '').toLowerCase())
  );
  const handleSubmit = async () => {
    const payload = {
      itemName: poItemList.itemName,
      category: poItemList.category, // This should already be the value like "CARPENTRY"
      otherPOEntityList: poItemList.otherPOEntityList,
    };

    try {
      const response = await fetch('https://backendaab.in/aabuildersDash/api/po_itemNames/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        throw new Error('Failed to submit data');
      }

      const data = await response.json();
      console.log('Success:', data);

      // Optionally show success message or close popup
      setIsPopupOpen(false);
    } catch (error) {
      console.error('Error:', error);
      alert('Failed to submit the form.');
    }
  };



  const customSelectStyles = {
    control: (provided, state) => ({
      ...provided,
      backgroundColor: "transparent",
      borderColor: state.isFocused ? "#FAF6ED" : "transparent",
      "&:hover": {
        borderColor: "none",
      },
      boxShadow: state.isFocused ? "0 0 0 #FAF6ED" : "none",
    }),
    dropdownIndicator: (base) => ({
      ...base,
      color: '#000',
    }),
    indicatorSeparator: () => ({
      display: 'none',
    }),
  };
  return (
    <div className="p-4 bg-white ml-6 mr-8">
      <div className="text-left mt-2">
        <h4 className=" font-semibold mb-2 ">Category</h4>
        <Select
          value={selectedCategory}
          onChange={setSelectedCategory}
          options={categoryOptions}
          placeholder="Select Category..."
          className="border border-[#FAF6ED] border-r-[0.25rem] border-l-[0.25rem] border-b-[0.25rem] border-t-[0.25rem] rounded-lg lg:w-[450px] w-64 h-12 text-left"
          styles={customSelectStyles}
          isClearable
        />
      </div>
      <div className=" lg:flex space-x-[2%] w-full mt-10 overflow-x-auto">
        <div>
          <div className="flex items-center mb-2">
            <input
              type="text"
              className="border border-[#FAF6ED] border-r-4 border-l-4 border-b-4 border-t-4 rounded-lg p-2 flex-1 w-[250px] h-12 focus:outline-none"
              placeholder="Search Category.."
              value={poCategorySearch}
              onChange={(e) => setPoCategorySearch(e.target.value)}
            />
            <button className="-ml-6 mt-5 transform -translate-y-1/2 text-gray-500">
              <img src={search} alt='search' className=' w-5 h-5' />
            </button>
            <button
              className="text-black font-bold px-1 ml-4 border-dashed border-b-2 border-[#BF9853]"
              onClick={() => setIsShowModal(true)}
            >
              + Add
            </button>
          </div>
          <button className="text-[#E4572E] -mb-4 flex ">
            <img src={imports} alt='import' className=' w-6 h-5 bg-transparent pr-2 mt-1' />
            <h1 className='mt-1.5 text-sm'>Import file</h1>
          </button>
          <button>
            <img src={deleteIcon} alt='del' className='-mb-14 mt-5 lg:ml-[23rem] ml-[15rem]' />
          </button>
          <div className="rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853]">
            <div className="bg-[#FAF6ED]">
              <table className="table-auto lg:w-[355px] w-72">
                <thead className="bg-[#FAF6ED]">
                  <tr className="border-b">
                    <th className="p-2 text-left w-16 text-xl font-bold">S.No</th>
                    <th className="p-2 text-left w-72 text-xl font-bold">Category</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div className="overflow-y-auto max-h-[660px] scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100">
              <table className="table-auto lg:w-96 w-72">
                <tbody>
                  {filteredPocategory.map((item, index) => (
                    <tr key={item.id} className="border-b odd:bg-white even:bg-[#FAF6ED]">
                      <td className="p-2 text-left font-semibold">{(poCategory.findIndex(acc => acc.id === item.id) + 1).toString().padStart(2, '0')}</td>
                      <td className="p-2 text-left group flex font-semibold">
                        <div className="flex flex-grow">
                          {item.category}
                        </div>
                        <div className="flex space-x-3 opacity-0 group-hover:opacity-100 transition-opacity duration-200 ">
                          <button type="button" >
                            <img src={edit} alt="add" className="w-4 h-4" type="button" onClick={() => openEditCategory(item)} />
                          </button>
                          <button >
                            <img src={deleteIcon} alt="delete" className="w-4 h-4" onClick={() => handleCategoryDelete(item.id)} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div>
          <div className="flex items-center mb-2">
            <input
              type="text"
              className="border border-[#FAF6ED] border-r-4 border-l-4 border-b-4 border-t-4 rounded-lg p-2 flex-1 w-[250px] h-12 focus:outline-none"
              placeholder="Search Tile Name and Size.."
            />
            <button className="-ml-6 mt-5 transform -translate-y-1/2 text-gray-500">
              <img src={search} alt='search' className=' w-5 h-5' />
            </button>
            <button
              onClick={() => {
                if (!selectedCategory) {
                  alert("Please select the category");
                } else {
                  setIsPopupOpen(true);
                }
              }}
              className="text-black font-bold px-1 ml-4 border-dashed border-b-2 border-[#BF9853]"
            >
              + Add
            </button>
          </div>
          <button className="text-[#E4572E] -mb-4 flex ">
            <img src={imports} alt='import' className=' w-6 h-5 bg-transparent pr-2 mt-1' />
            <h1 className='mt-1.5 text-sm'>Import file</h1>
          </button>
          <button>
            <img src={deleteIcon} alt='del' className='-mb-14 mt-5 lg:ml-[23rem] ml-[15rem]' />
          </button>
          <div className="rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853]">
            <div className="bg-[#FAF6ED]">
              <table className="table-auto lg:w-[355px] w-72">
                <thead className="bg-[#FAF6ED]">
                  <tr className="border-b">
                    <th className="p-2 text-left w-16 text-xl font-bold">S.No</th>
                    <th className="p-2 text-left w-72 text-xl font-bold">Item Name</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div className="overflow-y-auto max-h-[660px] scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100">
              <table className="table-auto lg:w-96 w-72">
                <tbody>
                  {filteredPoItemName.map((item, index) => (
                    <tr className="border-b odd:bg-white even:bg-[#FAF6ED]">
                      <td className="p-2 text-left font-semibold"></td>
                      <td className="p-2 group flex justify-between items-center font-semibold">
                        {item.itemName}
                        <div className="flex flex-grow">
                          <button className="font-medium hover:text-[#E4572E] text-left flex">
                          </button>
                        </div>
                        <div className="flex space-x-3 opacity-0 group-hover:opacity-100 transition-opacity duration-200 ">
                          <button
                            type="button"
                            onClick={() => {
                              if (!selectedCategory) {
                                alert("Please select the category");
                              } else {
                                openEditItemName(item); // or your edit logic here
                              }
                            }}
                          >
                            <img src={edit} alt="edit" className="w-4 h-4" />
                          </button>
                          <button>
                            <img src={deleteIcon} alt="delete" className="w-4 h-4" />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div>
          <div className="flex items-center mb-2 lg:mt-0 mt-3">
            <input
              type="text"
              className="border border-[#FAF6ED] border-r-4 border-l-4 border-b-4 border-t-4 rounded-lg p-2 flex-1 w-44 h-12 focus:outline-none"
              placeholder="Search Room Name.."
              value={poModelSearch}
              onChange={(e) => setPoModelSearch(e.target.value)}
            />
            <button className="-ml-6 mt-5 transform -translate-y-1/2 text-gray-500">
              <img src={search} alt='search' className=' w-5 h-5' />
            </button>
            <button
              onClick={() => setIsPopupOpen1(true)}
              className="text-black font-bold px-1 ml-4 border-dashed border-b-2 border-[#BF9853]"
            >
              + Add
            </button>
          </div>
          <button className="text-[#E4572E] -mb-4 flex"><img src={imports} alt='import' className=' w-6 h-5 bg-transparent pr-2 mt-1' /><h1 className='mt-1.5 text-sm'>Import file</h1></button>
          <button>
            <img src={deleteIcon} alt='del' className='-mb-14 mt-5 lg:ml-[19rem] ml-[12rem]' />
          </button>
          <div className='rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853]'>
            <div className="bg-[#FAF6ED]">
              <table className="table-auto lg:w-[249px] w-64">
                <thead className='bg-[#FAF6ED]'>
                  <tr className="border-b">
                    <th className="p-2 text-left w-16 text-xl font-bold">S.No</th>
                    <th className="p-2 text-left w-72 text-xl font-bold">Model</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div className="overflow-y-auto max-h-[660px] scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100">
              <table className="table-auto lg:w-80 w-64">
                <tbody>
                  {filteredPoModel.map((item, index) => (
                    <tr key={item.id} className="border-b odd:bg-white even:bg-[#FAF6ED]">
                      <td className="p-2 text-left font-semibold">{(poModel.findIndex(acc => acc.id === item.id) + 1).toString().padStart(2, '0')}</td>
                      <td className="p-2 text-left group flex font-semibold">
                        <div className="flex flex-grow">
                          {item.model}
                        </div>
                        <div className="flex space-x-3 opacity-0 group-hover:opacity-100 transition-opacity duration-200 ">
                          <button type="button" >
                            <img src={edit} alt="add" className="w-4 h-4" type="button" onClick={() => openEditModel(item)} />
                          </button>
                          <button >
                            <img src={deleteIcon} alt="delete" className="w-4 h-4" onClick={() => handleModelDelete(item.id)} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div>
          <div className="flex items-center mb-2 lg:mt-0 mt-3">
            <input
              type="text"
              className="border border-[#FAF6ED] border-r-4 border-l-4 border-b-4 border-t-4 rounded-lg p-2 flex-1 w-44 h-12 focus:outline-none"
              placeholder="Search Tile Size.."
              value={poBrandSearch}
              onChange={(e) => setPoBrandSearch(e.target.value)}
            />
            <button className="-ml-6 mt-5 transform -translate-y-1/2 text-gray-500">
              <img src={search} alt='search' className=' w-5 h-5' />
            </button>
            <button
              onClick={() => setIsPopupOpen2(true)}
              className="text-black font-bold ml-4 px-1 border-dashed border-b-2 border-[#BF9853]"
            >
              + Add
            </button>
          </div>
          <button className="text-[#E4572E] -mb-4 flex "><img src={imports} alt='import' className=' w-6 h-5 bg-transparent pr-2 mt-1' /><h1 className='mt-1.5 text-sm'>Import file</h1></button>
          <button>
            <img src={deleteIcon} alt='del' className='-mb-14 mt-5 ml-[17rem]' />
          </button>
          <div className='rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853]'>
            <div className="bg-[#FAF6ED]">
              <table className="table-auto lg:w-[323px] w-72">
                <thead className='bg-[#FAF6ED]'>
                  <tr className="border-b">
                    <th className="p-2 text-left w-16 text-xl font-bold">S.No</th>
                    <th className="p-2 text-left w-72 text-xl font-bold">Brand</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div className="overflow-y-auto max-h-[660px] scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100">
              <table className="table-auto lg:w-72 w-72">
                <tbody>
                  {filteredPoBrand.map((item, index) => (
                    <tr key={item.id} className="border-b odd:bg-white even:bg-[#FAF6ED]">
                      <td className="p-2 text-left font-semibold">{(poBrand.findIndex(acc => acc.id === item.id) + 1).toString().padStart(2, '0')}</td>
                      <td className="p-2 text-left group flex font-semibold">
                        <div className="flex flex-grow">
                          {item.brand}
                        </div>
                        <div className="flex space-x-3 opacity-0 group-hover:opacity-100 transition-opacity duration-200 ">
                          <button type="button" >
                            <img src={edit} alt="add" className="w-4 h-4" type="button" onClick={() => openEditBrand(item)} />
                          </button>
                          <button >
                            <img src={deleteIcon} alt="delete" className="w-4 h-4" onClick={() => handleBrandDelete(item.id)} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div>
          <div className="flex items-center mb-2 lg:mt-0 mt-3">
            <input
              type="text"
              className="border border-[#FAF6ED] border-r-4 border-l-4 border-b-4 border-t-4 rounded-lg p-2 flex-1 w-44 h-12 focus:outline-none "
              placeholder="Search Floor Name.."
              value={poTypeSearch}
              onChange={(e) => setPoTypeSearch(e.target.value)}
            />
            <button className="-ml-6 mt-5 transform -translate-y-1/2 text-gray-500">
              <img src={search} alt='search' className=' w-5 h-5' />
            </button>
            <button
              onClick={() => setIsPopupOpen3(true)}
              className="text-black font-bold ml-4 px-1 border-dashed border-b-2 border-[#BF9853]"
            >
              + Add
            </button>
          </div>
          <button className="text-[#E4572E] -mb-4 flex"><img src={imports} alt='import' className=' w-6 h-5 bg-transparent pr-2 mt-1' /><h1 className='mt-1.5 text-sm'>Import file</h1></button>
          <button>
            <img src={deleteIcon} alt='del' className='-mb-14 mt-5 ml-[15rem]' />
          </button>
          <div className='rounded-lg border border-gray-200 border-l-8 border-l-[#BF9853]'>
            <div className="bg-[#FAF6ED]">
              <table className="table-auto lg:w-[249px] w-72">
                <thead className='bg-[#FAF6ED]'>
                  <tr className="border-b">
                    <th className="p-2 text-left w-16 text-xl font-bold">S.No</th>
                    <th className="p-2 text-left w-auto text-xl font-bold">Type</th>
                  </tr>
                </thead>
              </table>
            </div>
            <div className="overflow-y-auto max-h-[660px] scrollbar-thin scrollbar-thumb-gray-300 scrollbar-track-gray-100">
              <table className="table-auto lg:w-72 w-72">
                <tbody>
                  {filteredPoTypeColor.map((item, index) => (
                    <tr key={item.id} className="border-b odd:bg-white even:bg-[#FAF6ED]">
                      <td className="p-2 text-left font-semibold">{(poType.findIndex(acc => acc.id === item.id) + 1).toString().padStart(2, '0')}</td>
                      <td className="p-2 text-left group flex font-semibold">
                        <div className="flex flex-grow">
                          {item.typeColor}
                        </div>
                        <div className="flex space-x-3 opacity-0 group-hover:opacity-100 transition-opacity duration-200 ">
                          <button type="button" >
                            <img src={edit} alt="add" className="w-4 h-4" type="button" onClick={() => openEditTypeColor(item)} />
                          </button>
                          <button >
                            <img src={deleteIcon} alt="delete" className="w-4 h-4" onClick={() => handleTypeDelete(item.id)} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      {showModal && (
        <div className="fixed inset-0 z-50 bg-black bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded shadow-lg w-[90%] max-w-md">
            <h2 className="text-xl font-semibold mb-4">Input Data</h2>

            <div className="space-y-3 max-h-[60vh] overflow-auto">
              {fields.map((field, index) => (
                <div className="flex items-center gap-2" key={index}>
                  <input
                    type={field.inputType}
                    placeholder={field.label || 'Field'}
                    className="flex-1 border px-3 py-2 rounded"
                  />
                  <select className="border px-2 py-2 rounded">
                    <option>{field.dropdown}</option>
                  </select>
                  <button
                    className="text-red-500"
                    onClick={() => handleRemoveField(index)}
                  >
                    <img src={deleteIcon} alt='#' className='w-4 h-4'></img>
                  </button>
                </div>
              ))}
              <p
                className="text-[#E4572E] font-semibold text-sm cursor-pointer text-left border-b-2 border-dashed w-16"
                onClick={handleAddField}
              >
                + Add on
              </p>
            </div>

            <div className="mt-4 flex justify-end gap-2">
              <button
                className="bg-[#BF9853] text-white px-4 py-2 rounded"
                onClick={() => setShowModal(false)}
              >
                Submit
              </button>
              <button
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded"
                onClick={() => setShowModal(false)}
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      )}
      {isShowModal && (
        <div className="fixed inset-0 bg-black bg-opacity-30 flex justify-center items-center z-50">
          <div className="bg-white w-[500px] rounded-md shadow-lg p-6 relative">
            <div className="flex justify-between items-center mb-4 text-left">
              <h2 className="text-lg font-semibold"></h2>
              <button
                className="text-red-500 text-xl font-bold"
                onClick={() => setIsShowModal(false)}
              >
                ×
              </button>
            </div>

            <label className="block text-[15px] font-medium text-gray-400 mb-1 text-left">
              Category Name <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              value={category}
              onChange={(e) => setCategory(e.target.value)}
              className="border-2 border-[#BF9853] border-opacity-25 rounded-md p-2 w-full mb-4 focus:outline-none"
              placeholder="Enter category"
            />

            <div className="flex justify-end gap-3">
              <button
                className="border border-[#BF9853] text-[#BF9853] rounded px-4 py-1"
                onClick={() => setIsShowModal(false)}
              >
                Close
              </button>
              <button
                className="bg-[#BF9853] text-white rounded px-4 py-1"
                onClick={handleSubmitCategary}
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isCategoryEditPopupOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-30 flex justify-center items-center z-50">
          <div className="bg-white w-[500px] rounded-md shadow-lg p-6 relative">
            <div className="flex justify-between items-center border-b pb-2 mb-4 text-left">
              <h2 className="text-lg font-semibold">Edit CATEGORY</h2>
              <button
                className="text-red-500 text-xl font-bold"
                onClick={() => setIsCategoryEditPopupOpen(false)}
              >
                ×
              </button>
            </div>

            <label className="block text-[15px] font-medium text-gray-400 mb-1 text-left">
              Category Name <span className="text-red-500">*</span>
            </label>
            <input
              type="text"
              value={editCategory}
              onChange={(e) => setEditCategory(e.target.value)}
              className="border-2 border-[#BF9853] border-opacity-25 rounded-md p-2 w-full mb-4 focus:outline-none"
              placeholder="Enter category"
            />

            <div className="flex justify-end gap-3">
              <button
                className="border border-[#BF9853] text-[#BF9853] rounded px-4 py-1"
                onClick={() => setIsCategoryEditPopupOpen(false)}
              >
                Close
              </button>
              <button
                className="bg-[#BF9853] text-white rounded px-4 py-1"
                onClick={handleSubmitEditCategory}
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isPopupOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
          <div className="bg-white rounded-md w-[1050px] shadow-lg text-left p-6">
            <div className="flex justify-between items-center border-b pb-2 mb-4">
              <h2 className="text-lg font-semibold"></h2>
              <button onClick={() => setIsPopupOpen(false)} className="text-red-500 text-[30px] font-bold">×</button>
            </div>

            {/* Modal Form */}
            <div className="space-y-4">
              <div>
                <label className="block font-medium text-gray-400 text-[15px]">Item Name <span className="text-red-500">*</span></label>
                <input
                  type="text"
                  value={poItemList.itemName}
                  onChange={(e) => setPoItemList({ ...poItemList, itemName: e.target.value })}
                  className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                  placeholder="Test item"
                />
              </div>

              {poItemList.otherPOEntityList.map((item, index) => (
                <div key={index} className="flex flex-wrap gap-4 items-end mb-2">
                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Model Name <span className="text-red-500">*</span></label>
                    <Select
                      options={modelOptions}
                      isClearable={true}
                      value={item.modelName ? { value: item.modelName, label: item.modelName } : null}
                      onChange={(selectedOption) => {
                        const updatedList = [...poItemList.otherPOEntityList];
                        updatedList[index].modelName = selectedOption ? selectedOption.value : '';
                        setPoItemList({ ...poItemList, otherPOEntityList: updatedList });
                      }}
                      styles={{
                        container: (base) => ({ ...base, width: 230 }),
                      }}
                    />
                  </div>

                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Brand Name <span className="text-red-500">*</span></label>
                    <Select
                      options={brandOptions}
                      isClearable={true}
                      value={item.brandName ? { value: item.brandName, label: item.brandName } : null}
                      onChange={(selectedOption) => {
                        const updatedList = [...poItemList.otherPOEntityList];
                        updatedList[index].brandName = selectedOption ? selectedOption.value : '';
                        setPoItemList({ ...poItemList, otherPOEntityList: updatedList });
                      }}
                      styles={{
                        container: (base) => ({ ...base, width: 230 }),
                      }}
                    />
                  </div>

                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Type/Color <span className="text-red-500">*</span></label>
                    <Select
                      options={typeColorOptions}
                      isClearable={true}
                      value={item.typeColor ? { value: item.typeColor, label: item.typeColor } : null}
                      onChange={(selectedOption) => {
                        const updatedList = [...poItemList.otherPOEntityList];
                        updatedList[index].typeColor = selectedOption ? selectedOption.value : '';
                        setPoItemList({ ...poItemList, otherPOEntityList: updatedList });
                      }}
                      styles={{
                        container: (base) => ({ ...base, width: 230 }),
                      }}
                    />
                  </div>

                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Min Qty <span className="text-red-500">*</span></label>
                    <input
                      type="number"
                      className="w-28 border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none no-spinner"
                      value={item.minimumQty}
                      onChange={(e) => {
                        const updatedList = [...poItemList.otherPOEntityList];
                        updatedList[index].minimumQty = parseInt(e.target.value, 10) || '';
                        setPoItemList({ ...poItemList, otherPOEntityList: updatedList });
                      }}
                    />
                  </div>

                  <div className="flex items-center gap-2">
                    <div>
                      <label className="block font-medium text-gray-400 text-[15px]">Default Qty <span className="text-red-500">*</span></label>
                      <input
                        type="number"
                        className="w-28 border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none no-spinner"
                        value={item.defaultQty}
                        onChange={(e) => {
                          const updatedList = [...poItemList.otherPOEntityList];
                          updatedList[index].defaultQty = parseInt(e.target.value, 10) || '';
                          setPoItemList({ ...poItemList, otherPOEntityList: updatedList });
                        }}
                      />
                    </div>

                    {/* ❌ Remove button */}
                    {poItemList.otherPOEntityList.length > 1 && (
                      <button
                        onClick={() => {
                          const updatedList = poItemList.otherPOEntityList.filter((_, i) => i !== index);
                          setPoItemList({ ...poItemList, otherPOEntityList: updatedList });
                        }}
                        className="text-red-500 text-[25px] font-bold mt-6"
                        title="Remove this row"
                      >
                        ×
                      </button>
                    )}
                  </div>
                </div>
              ))}
              <button
                onClick={() => {
                  setPoItemList((prev) => ({
                    ...prev,
                    otherPOEntityList: [
                      ...prev.otherPOEntityList,
                      {
                        modelName: '',
                        brandName: '',
                        typeColor: '',
                        minimumQty: '',
                        defaultQty: '',
                      },
                    ],
                  }));
                }}
                className="text-[#BF9853] font-semibold mt-2"
              >
                + Add on
              </button>
            </div>

            {/* Footer */}
            <div className="flex justify-end mt-6 space-x-3">
              <button
                onClick={() => setIsPopupOpen(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded"
              >
                Close
              </button>
              <button
                onClick={handleSubmit}
                className="bg-[#BF9853] text-white px-4 py-2 rounded"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isItemNameEditPopupOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-40">
          <div className="bg-white rounded-md w-[1050px] shadow-lg text-left p-6">
            <div className="flex justify-between items-center border-b pb-2 mb-4">
              <h2 className="text-lg font-semibold"></h2>
              <button onClick={() => setIsItemNameEditPopupOpen(false)} className="text-red-500 text-[30px] font-bold">×</button>
            </div>

            {/* Modal Form */}
            <div className="space-y-4">
              <div>
                <label className="block font-medium text-gray-400 text-[15px]">Item Name <span className="text-red-500">*</span></label>
                <input
                  type="text"
                  value={poEditItemList.itemName}
                  onChange={(e) => setPoEditItemList({ ...poEditItemList, itemName: e.target.value })}
                  className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                  placeholder="Test item"
                />
              </div>

              {poEditItemList.otherPOEntityList.map((item, index) => (
                <div key={index} className="flex flex-wrap gap-4 items-end mb-2">
                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Model Name <span className="text-red-500">*</span></label>
                    <Select
                      options={modelOptions}
                      isClearable={true}
                      value={item.modelName ? { value: item.modelName, label: item.modelName } : null}
                      onChange={(selectedOption) => {
                        const updatedList = [...poEditItemList.otherPOEntityList];
                        updatedList[index].modelName = selectedOption ? selectedOption.value : '';
                        setPoEditItemList({ ...poEditItemList, otherPOEntityList: updatedList });
                      }}
                      styles={{
                        container: (base) => ({ ...base, width: 230 }),
                      }}
                    />
                  </div>

                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Brand Name <span className="text-red-500">*</span></label>
                    <Select
                      options={brandOptions}
                      isClearable={true}
                      value={item.brandName ? { value: item.brandName, label: item.brandName } : null}
                      onChange={(selectedOption) => {
                        const updatedList = [...poEditItemList.otherPOEntityList];
                        updatedList[index].brandName = selectedOption ? selectedOption.value : '';
                        setPoEditItemList({ ...poEditItemList, otherPOEntityList: updatedList });
                      }}
                      styles={{
                        container: (base) => ({ ...base, width: 230 }),
                      }}
                    />
                  </div>

                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Type/Color <span className="text-red-500">*</span></label>
                    <Select
                      options={typeColorOptions}
                      isClearable={true}
                      value={item.typeColor ? { value: item.typeColor, label: item.typeColor } : null}
                      onChange={(selectedOption) => {
                        const updatedList = [...poEditItemList.otherPOEntityList];
                        updatedList[index].typeColor = selectedOption ? selectedOption.value : '';
                        setPoEditItemList({ ...poEditItemList, otherPOEntityList: updatedList });
                      }}
                      styles={{
                        container: (base) => ({ ...base, width: 230 }),
                      }}
                    />
                  </div>

                  <div>
                    <label className="block font-medium text-gray-400 text-[15px]">Min Qty <span className="text-red-500">*</span></label>
                    <input
                      type="number"
                      className="w-28 border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none no-spinner"
                      value={item.minimumQty}
                      onChange={(e) => {
                        const updatedList = [...poEditItemList.otherPOEntityList];
                        updatedList[index].minimumQty = parseInt(e.target.value, 10) || '';
                        setPoEditItemList({ ...poEditItemList, otherPOEntityList: updatedList });
                      }}
                    />
                  </div>

                  <div className="flex items-center gap-2">
                    <div>
                      <label className="block font-medium text-gray-400 text-[15px]">Default Qty <span className="text-red-500">*</span></label>
                      <input
                        type="number"
                        className="w-28 border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none no-spinner"
                        value={item.defaultQty}
                        onChange={(e) => {
                          const updatedList = [...poEditItemList.otherPOEntityList];
                          updatedList[index].defaultQty = parseInt(e.target.value, 10) || '';
                          setPoEditItemList({ ...poEditItemList, otherPOEntityList: updatedList });
                        }}
                      />
                    </div>

                    {/* ❌ Remove button */}
                    {poEditItemList.otherPOEntityList.length > 1 && (
                      <button
                        onClick={() => {
                          const updatedList = poEditItemList.otherPOEntityList.filter((_, i) => i !== index);
                          setPoEditItemList({ ...poEditItemList, otherPOEntityList: updatedList });
                        }}
                        className="text-red-500 text-[25px] font-bold mt-6"
                        title="Remove this row"
                      >
                        ×
                      </button>
                    )}
                  </div>
                </div>
              ))}
              <button
                onClick={() => {
                  setPoItemList((prev) => ({
                    ...prev,
                    otherPOEntityList: [
                      ...prev.otherPOEntityList,
                      {
                        modelName: '',
                        brandName: '',
                        typeColor: '',
                        minimumQty: '',
                        defaultQty: '',
                      },
                    ],
                  }));
                }}
                className="text-[#BF9853] font-semibold mt-2"
              >
                + Add on
              </button>
            </div>

            {/* Footer */}
            <div className="flex justify-end mt-6 space-x-3">
              <button
                onClick={() => setIsItemNameEditPopupOpen(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded"
              >
                Close
              </button>
              <button
                onClick={handleSubmitEditItemName}
                className="bg-[#BF9853] text-white px-4 py-2 rounded"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isPopupOpen1 && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg w-[500px] shadow-lg p-6 text-left">
            {/* Header */}
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-lg font-semibold"></h2>
              <button
                onClick={() => setIsPopupOpen1(false)}
                className="text-red-500 text-xl font-bold"
              >
                ×
              </button>
            </div>

            {/* Model Name Input */}
            <div className="mb-6">
              <label className="block text-[15px] font-medium text-gray-400 mb-1">
                Model Name <span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                value={model}
                onChange={(e) => setModel(e.target.value)}
                className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                placeholder="Enter model name"
              />
            </div>

            {/* Footer Buttons */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsPopupOpen1(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded text-sm"
              >
                Close
              </button>
              <button
                onClick={handleSubmitModel}
                className="bg-[#BF9853] text-white px-4 py-2 rounded text-sm"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isModelEditPopupOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg w-[500px] shadow-lg p-6 text-left">
            {/* Header */}
            <div className="flex justify-between items-center border-b pb-2 mb-4">
              <h2 className="text-lg font-semibold">Edit MODEL</h2>
              <button
                onClick={() => setIsModelEditPopupOpen(false)}
                className="text-red-500 text-xl font-bold"
              >
                ×
              </button>
            </div>

            {/* Model Name Input */}
            <div className="mb-6">
              <label className="block text-[15px] font-medium text-gray-400 mb-1">
                Model Name <span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                value={editModel}
                onChange={(e) => setEditModel(e.target.value)}
                className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                placeholder="Enter model name"
              />
            </div>

            {/* Footer Buttons */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsModelEditPopupOpen(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded text-sm"
              >
                Close
              </button>
              <button
                onClick={handleSubmitEditModel}
                className="bg-[#BF9853] text-white px-4 py-2 rounded text-sm"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isPopupOpen2 && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg w-[500px] shadow-lg p-6 text-left">
            {/* Header */}
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-lg font-semibold"></h2>
              <button
                onClick={() => setIsPopupOpen2(false)}
                className="text-red-500 text-xl font-bold"
              >
                ×
              </button>
            </div>

            {/* Brand Name Input */}
            <div className="mb-6">
              <label className="block text-[15px] font-medium text-gray-400 mb-1">
                Brand Name <span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                value={brand}
                onChange={(e) => setBrand(e.target.value)}
                className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                placeholder="Enter brand name"
              />
            </div>

            {/* Footer Buttons */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsPopupOpen2(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded text-sm"
              >
                Close
              </button>
              <button
                onClick={handleSubmitBrand}
                className="bg-[#BF9853] text-white px-4 py-2 rounded text-sm"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isBrandEditPopupOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg w-[500px] shadow-lg p-6 text-left">
            {/* Header */}
            <div className="flex justify-between items-center border-b pb-2 mb-4">
              <h2 className="text-lg font-semibold">Edit BRAND</h2>
              <button
                onClick={() => setIsBrandEditPopupOpen(false)}
                className="text-red-500 text-xl font-bold"
              >
                ×
              </button>
            </div>

            {/* Brand Name Input */}
            <div className="mb-6">
              <label className="block text-[15px] font-medium text-gray-400 mb-1">
                Brand Name <span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                value={editBrand}
                onChange={(e) => setEditBrand(e.target.value)}
                className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                placeholder="Enter brand name"
              />
            </div>

            {/* Footer Buttons */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsBrandEditPopupOpen(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded text-sm"
              >
                Close
              </button>
              <button
                onClick={handleSubmitEditBrand}
                className="bg-[#BF9853] text-white px-4 py-2 rounded text-sm"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isPopupOpen3 && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg w-[500px] shadow-lg p-6 text-left">
            {/* Header */}
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-lg font-semibold"></h2>
              <button
                onClick={() => setIsPopupOpen3(false)}
                className="text-red-500 text-xl font-bold"
              >
                ×
              </button>
            </div>

            {/* Type Input Field */}
            <div className="mb-6">
              <label className="block text-[15px] font-medium text-gray-400 mb-1">
                Type <span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                value={typeColor}
                onChange={(e) => setTypeColor(e.target.value)}
                className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded"
                placeholder="Enter type name"
              />
            </div>

            {/* Footer */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsPopupOpen3(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded text-sm"
              >
                Close
              </button>
              <button
                onClick={handleSubmitType}
                className="bg-[#BF9853] text-white px-4 py-2 rounded text-sm"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
      {isTypeEditPopupOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
          <div className="bg-white rounded-lg w-[500px] shadow-lg p-6 text-left">
            {/* Header */}
            <div className="flex justify-between items-center border-b pb-2 mb-4">
              <h2 className="text-lg font-semibold">Edit TYPE</h2>
              <button
                onClick={() => setIsTypeEditPopupOpen(false)}
                className="text-red-500 text-xl font-bold"
              >
                ×
              </button>
            </div>

            {/* Type Input Field */}
            <div className="mb-6">
              <label className="block text-[15px] font-medium text-gray-400 mb-1">
                Type <span className="text-red-500">*</span>
              </label>
              <input
                type="text"
                value={editTypeColor}
                onChange={(e) => setEditTypeColor(e.target.value)}
                className="w-full border-2 border-[#BF9853] border-opacity-25 p-2 rounded focus:outline-none"
                placeholder="Enter type name"
              />
            </div>

            {/* Footer */}
            <div className="flex justify-end space-x-4">
              <button
                onClick={() => setIsTypeEditPopupOpen(false)}
                className="border border-[#BF9853] text-[#BF9853] px-4 py-2 rounded text-sm"
              >
                Close
              </button>
              <button
                onClick={handleSubmitEditType}
                className="bg-[#BF9853] text-white px-4 py-2 rounded text-sm"
              >
                Submit
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

export default PurchaseInputData